package cool.leeson.library.service.receive;

import com.aliyuncs.utils.StringUtils;
import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.config.RedisConfig;
import cool.leeson.library.dao.LibraryDao;
import cool.leeson.library.dao.LibraryRoomDao;
import cool.leeson.library.dao.LibrarySeatDao;
import cool.leeson.library.dao.ReceiveItemDao;
import cool.leeson.library.entity.receive.ReceiveItem;
import cool.leeson.library.entity.receive.ReceiveItemPost;
import cool.leeson.library.entity.receive.ReceiveItemResponse;
import cool.leeson.library.entity.user.UserOnline;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.library.LibraryRoomService;
import cool.leeson.library.service.library.LibrarySeatService;
import cool.leeson.library.service.library.LibraryService;
import cool.leeson.library.service.library.SchoolService;
import cool.leeson.library.service.user.UserOnlineService;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * (ReceiveOrder)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-03-17 16:43:01
 */
@Service("receiveService")
@Slf4j
@Transactional
public class ReceiveService {
    @Resource
    private ReceiveItemDao receiveItemDao;
    @Resource
    private LibraryDao libraryDao;
    @Resource
    private LibraryRoomDao libraryRoomDao;
    @Resource
    private LibrarySeatDao librarySeatDao;

    @Resource
    private SchoolService schoolService;
    @Resource
    private LibraryService libraryService;
    @Resource
    private LibraryRoomService libraryRoomService;
    @Resource
    private LibrarySeatService librarySeatService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private UserOnlineService userOnlineService;

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtConfig jwtConfig;
    //    seatKey
    private String seatKeyFormat = "%s:%s:%s"; // seatId:day:timeIdx   dsackacbjakcaw3:16:1
    // 用户key
    private String userKeyFormat = "%s:%s:%s";  // userId:day:timeIdx  dfjank:12:0
    private String onlineKeyFormat = "%s:online";  // userId:online  dfjank:online

    /**
     * 提交预约订单
     *
     * @param receiveItemPosts 数据
     * @return 实体
     */
    public Map<String, Object> receive(List<ReceiveItemPost> receiveItemPosts, String userId) throws MyException {
        if (receiveItemPosts == null || receiveItemPosts.size() == 0)
            throw new MyException(MyException.STATUS.requestErr);
//        log.info(receiveItemPosts.toString());
        Date now = new Date();
        // 构建 receiveItems
        List<ReceiveItem> receiveItems = new ArrayList<>();
        for (ReceiveItemPost receiveItemPost : receiveItemPosts) {
            int today = receiveItemPost.getToday() ? 0 : 1;
            // 查询座位是否占用
            String seatKey = String.format(seatKeyFormat, receiveItemPost.getSeatId(), now.getDate() + today, receiveItemPost.getTimeIdx());
            String rSeatRecord = redisTemplate.opsForValue().get(seatKey);
            if (!StringUtils.isEmpty(rSeatRecord) && "true".equals(rSeatRecord)) {
                // 说明有数据
                return ResMap.err("座位已占座，请刷新");
            }
            // 查询用户在该时段是否预约
            String userKey = String.format(userKeyFormat, userId, now.getDate() + today, receiveItemPost.getTimeIdx());
            String rUserRecord = redisTemplate.opsForValue().get(userKey);
            if (!StringUtils.isEmpty(rUserRecord) && "true".equals(rUserRecord)) {
                // 说明有数据
                return ResMap.err("您已经预约了该时段");
            }
            // 构建 receiveItem

//            LocalTime local = LocalTime.of(timeIdx * 2 + 8, 0, 0, 0); 构建时间的
            ReceiveItem receiveItem = new ReceiveItem(UUID.randomUUID().toString(), userId, receiveItemPost.getLibraryId(), receiveItemPost.getRoomId(), receiveItemPost.getSeatId(), new Date(now.getYear(), now.getMonth(), now.getDate() + today), receiveItemPost.getTimeIdx(), now);
            receiveItems.add(receiveItem);
        }
        // 插入 items
        if (this.receiveItemDao.insertBatch(receiveItems) < receiveItems.size()) {
            throw new MyException(MyException.STATUS.err);
        }
        // 座位和用户信息，插入redis
        for (ReceiveItemPost receiveItemPost : receiveItemPosts) {
            int today = receiveItemPost.getToday() ? 0 : 1;
            String seatKey = String.format(seatKeyFormat, receiveItemPost.getSeatId(), now.getDate() + today, receiveItemPost.getTimeIdx());
            String userKey = String.format(userKeyFormat, userId, now.getDate() + today, receiveItemPost.getTimeIdx());
            // 剩下的时间
            long milliSecondsLeftToday = 86400000 - DateUtils.getFragmentInMilliseconds(Calendar.getInstance(), Calendar.DATE);
            milliSecondsLeftToday = receiveItemPost.getToday() ? milliSecondsLeftToday : milliSecondsLeftToday + 86400000;
            // 插入
            redisTemplate.opsForValue().set(seatKey, "true", milliSecondsLeftToday, TimeUnit.MILLISECONDS);
            redisTemplate.opsForValue().set(userKey, "true", milliSecondsLeftToday, TimeUnit.MILLISECONDS);
        }
        return this.schedule(userId);
    }

    /**
     * 取消预约
     *
     * @param userId    用户Id
     * @param receiveId 预约Id
     * @return 实体
     * @throws MyException w
     */
    public Map<String, Object> cancel(String userId, String receiveId) throws MyException {
        if (StringUtils.isEmpty(receiveId)) return ResMap.err("请上传receiveId");
        // 查询receive
        ReceiveItem receiveItem = this.receiveItemDao.queryById(receiveId);
        if (receiveItem == null) {
            throw new MyException(MyException.STATUS.requestErr);
        }
        // 获取取消的时间
        Date receiveDate = receiveItem.getReceiveDate();
        Integer timeIdx = receiveItem.getTimeIdx();
        Date now = new Date();
        int idx = (now.getHours() - 8) / 2;
        Date date = new Date(now.getYear(), now.getMonth(), now.getDate());
        // 判断时间是否相同
        if (receiveDate.equals(date) && idx == timeIdx) {
            // 判断用户是否在线
            Map<String, Object> map = userOnlineService.queryById(userId);
            UserOnline userOnline = (UserOnline) map.get("data");
            Integer online = userOnline.getOnline();
            if (online == 1) {
                return ResMap.err("请离座扫码");
            }
            userOnline.setOnline(0);
            userOnlineService.update(userOnline);
        }
        if (receiveItemDao.deleteById(receiveId) != 1) {
            throw new MyException(MyException.STATUS.err);
        }
        String userKey = String.format(RedisConfig.FormatKey.USERRECEIVE.toString(), userId, receiveDate.getDate(), timeIdx);
        String seatKey = String.format(RedisConfig.FormatKey.SEATRECEIVE.toString(), receiveItem.getSeatId(), receiveDate.getDate(), timeIdx);
        redisTemplate.opsForValue().set(userKey, "false", 1, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(seatKey, "false", 1, TimeUnit.SECONDS);
        return ResMap.ok();
    }

    /**
     * 获取用户的行程计划
     *
     * @param userId 用户Id
     * @return 实体
     * @throws MyException ms
     */
    public Map<String, Object> schedule(String userId) throws MyException {
        log.info(userId + " 正在获取行程计划");
        // 先查询所有
        List<ReceiveItemResponse> items = (List<ReceiveItemResponse>) this.queryAll(userId).get("data");
        Date now = new Date();
        // 时间Idx
        int timeIndex = 0;
        int today = 0;
        if (now.getHours() < 10) {
            timeIndex = 0;
        } else if (now.getHours() >= 22) {
            // 第二天
            timeIndex = 0;
            today = 1;
        } else {
            timeIndex = (now.getHours() - 8) / 2;
        }
        // 要展现的时间 年月日
        Date date = new Date(now.getYear(), now.getMonth(), now.getDate() + today);
        List<ReceiveItemResponse> responseItems = new ArrayList<>();
        for (ReceiveItemResponse item : items) {
            // 比这个时间早，直接继续 日期早或index小
            if (item.getReceiveDate().before(date) || (item.getReceiveDate().equals(date) && item.getTimeIdx() < timeIndex)) {
                continue;
            }
            responseItems.add(item);
        }
        // 排序
        Collections.sort(responseItems);
        return ResMap.ok(responseItems);

    }

    /**
     * 获取用户的所有预约
     *
     * @param userId 用户Id
     * @return 实体
     * @throws MyException ms
     */
    public Map<String, Object> queryAll(String userId) throws MyException {
        log.info(userId + " 正在获取行程计划");
        // 先查询所有
        List<ReceiveItem> items = this.receiveItemDao.queryByUserId(userId);
        List<ReceiveItemResponse> responseItems = new ArrayList<>();
        for (ReceiveItem item : items) {
            // 构建
            ReceiveItemResponse responseItem = new ReceiveItemResponse(item);
            // date
            String ddate = item.getReceiveDate().getMonth() + 1 + "月" + item.getReceiveDate().getDate() + "日";
            // 获取名字
            String libraryName = this.libraryService.querySimple(item.getLibraryId()).getName();
            String roomName = this.libraryRoomService.querySimple(item.getRoomId()).getName();
            String seatName = this.librarySeatService.querySimple(item.getSeatId()).getName();
            responseItem.setDate(ddate);
            responseItem.setLibraryName(libraryName);
            responseItem.setRoomName(roomName);
            responseItem.setSeatName(seatName);
            responseItems.add(responseItem);
        }
        // 排序
        Collections.sort(responseItems);
        return ResMap.ok(responseItems);
    }


}
