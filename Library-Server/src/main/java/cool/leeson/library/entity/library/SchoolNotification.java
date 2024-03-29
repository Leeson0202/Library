package cool.leeson.library.entity.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * (SchoolNotification)实体类
 *
 * @author Leeson0202
 * @since 2023-04-10 00:28:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolNotification implements Serializable {
    private static final long serialVersionUID = -26626992453291970L;
    /**
     * id
     */
    private String notificationId;
    /**
     * 学校id
     */
    @NotBlank(message = "schoolId 不能为空")
    private String schoolId;
    /**
     * 用户id
     */
    @NotBlank(message = "userId 不能为空")
    private String userId;
    private String nickName;
    /**
     * 标题
     */
    @NotBlank(message = "title 不能为空")
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "context 不能为空")
    private String context;
    /**
     * 浏览量
     */
    private Integer view;
    /**
     * 发布日期
     */
    private Date date;


}

