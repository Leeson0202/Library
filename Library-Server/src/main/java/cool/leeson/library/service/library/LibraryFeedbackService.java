package cool.leeson.library.service.library;

import cool.leeson.library.dao.LibraryFeedbackDao;
import cool.leeson.library.entity.library.LibraryFeedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * (LibraryFeedback)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-04-12 16:35:47
 */
@Service("libraryFeedbackService")
public class LibraryFeedbackService {
    @Resource
    private LibraryFeedbackDao libraryFeedbackDao;

    /**
     * 通过ID查询单条数据
     *
     * @param feedbackId 主键
     * @return 实例对象
     */
    public LibraryFeedback queryById(String feedbackId) {
        return this.libraryFeedbackDao.queryById(feedbackId);
    }

    /**
     * 分页查询
     *
     * @param libraryFeedback 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    public Page<LibraryFeedback> queryByPage(LibraryFeedback libraryFeedback, PageRequest pageRequest) {
        long total = this.libraryFeedbackDao.count(libraryFeedback);
        return new PageImpl<>(this.libraryFeedbackDao.queryAllByLimit(libraryFeedback, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param libraryFeedback 实例对象
     * @return 实例对象
     */
    public LibraryFeedback insert(LibraryFeedback libraryFeedback) {
        libraryFeedback.setFeedbackId(UUID.randomUUID().toString());
        libraryFeedback.setTag(0);
        libraryFeedback.setDate(new Date());
        this.libraryFeedbackDao.insert(libraryFeedback);
        return libraryFeedback;
    }

    /**
     * 修改数据
     *
     * @param libraryFeedback 实例对象
     * @return 实例对象
     */
    public LibraryFeedback update(LibraryFeedback libraryFeedback) {
        this.libraryFeedbackDao.update(libraryFeedback);
        return this.queryById(libraryFeedback.getFeedbackId());
    }

    /**
     * 通过主键删除数据
     *
     * @param feedbackId 主键
     * @return 是否成功
     */
    public boolean deleteById(String feedbackId) {
        return this.libraryFeedbackDao.deleteById(feedbackId) > 0;
    }
}
