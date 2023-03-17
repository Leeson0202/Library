package cool.leeson.library.service.receive;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.dao.*;
import cool.leeson.library.entity.receive.ReceiveItem;
import cool.leeson.library.entity.receive.ReceiveItemResponse;
import cool.leeson.library.entity.receive.ReceiveOrder;
import cool.leeson.library.entity.receive.ReceivePost;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.*;

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
    private ReceiveOrderDao receiveOrderDao;
    @Resource
    private ReceiveItemDao receiveItemDao;
    @Resource
    private LibraryDao libraryDao;
    @Resource
    private LibraryRoomDao libraryRoomDao;
    @Resource
    private LibrarySeatDao librarySeatDao;
    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtConfig jwtConfig;

    /**
     * 提交预约订单
     *
     * @param receivePosts 数据
     * @return 实体
     */
    public Map<String, Object> order(List<ReceivePost> receivePosts) throws MyException {
        if (receivePosts == null || receivePosts.size() == 0)
            throw new MyException(MyException.STATUS.requestErr);
        log.info(receivePosts.toString());
        // 数据
        String userId = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        String orderId = UUID.randomUUID().toString();
        Date now = new Date();
        //  receiveOrder
        ReceiveOrder receiveOrder = new ReceiveOrder();
        receiveOrder.setOrderId(orderId);
        receiveOrder.setUserId(userId);
        receiveOrder.setTime(now);
        // receiveItems
        List<ReceiveItem> receiveItems = new ArrayList<>();
        for (ReceivePost receivePost : receivePosts) {
            Integer today = receivePost.getToday() ? 0 : 1;
            Integer timeIdx = receivePost.getTimeIdx();
            ReceiveItem receiveItem = new ReceiveItem(UUID.randomUUID().toString(),
                    orderId, userId, receivePost.getLibraryId(),
                    receivePost.getRoomId(), receivePost.getSeatId(),
                    new Date(now.getYear(), now.getMonth(), now.getDate() + today),
                    LocalTime.of(timeIdx * 2 + 8, 0, 0, 0));
            receiveItems.add(receiveItem);
        }
        // 插入order
        if (this.receiveOrderDao.insert(receiveOrder) < 1) {
            throw new MyException(MyException.STATUS.err);
        }
        // 插入 items
        if (this.receiveItemDao.insertBatch(receiveItems) < receiveItems.size()) {
            throw new MyException(MyException.STATUS.err);
        }
        return this.queryById(orderId);
    }

    /**
     * 查询 预约订单列表 userId
     *
     * @param userId userId
     * @return 实体
     * @throws MyException m
     */
    public Map<String, Object> query(String userId) throws MyException {
        List<ReceiveOrder> receiveOrders = this.receiveOrderDao.queryByUserId(userId);
        for (ReceiveOrder receiveOrder : receiveOrders) {
            List<ReceiveItem> receiveItems = this.receiveItemDao.queryByOrderId(receiveOrder.getOrderId());
            receiveOrder.setReceiveItems(receiveItems);
        }
        return ResMap.ok(receiveOrders);
    }

    /**
     * 查询 预约订单 orderId
     *
     * @param orderId orderId
     * @return 实体
     * @throws MyException m
     */
    public Map<String, Object> queryById(String orderId) throws MyException {
        ReceiveOrder receiveOrder = this.receiveOrderDao.queryById(orderId);
        if (receiveOrder == null) {
            throw new MyException(MyException.STATUS.requestErr);
        }
        List<ReceiveItem> receiveItems = this.receiveItemDao.queryByOrderId(receiveOrder.getOrderId());
        List<ReceiveItemResponse> receiveItemResponses = new ArrayList<>();
        try {
            for (ReceiveItem receiveItem : receiveItems) {
                ReceiveItemResponse receiveItemResponse = new ReceiveItemResponse(receiveItem);
                receiveItemResponse.setLibraryName(this.libraryDao.queryById(receiveItem.getLibraryId()).getName());
                receiveItemResponse.setRoomName(this.libraryRoomDao.queryById(receiveItem.getRoomId()).getName());
                receiveItemResponse.setSeatName(this.librarySeatDao.queryById(receiveItem.getSeatId()).getName());
                receiveItemResponses.add(receiveItemResponse);
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
            return ResMap.put(MyException.STATUS.err);
        }

        receiveOrder.setReceiveItemResponses(receiveItemResponses);
        return ResMap.ok(receiveOrder);
    }


}
