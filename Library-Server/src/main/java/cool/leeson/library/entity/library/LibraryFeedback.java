package cool.leeson.library.entity.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * (LibraryFeedback)实体类
 *
 * @author Leeson0202
 * @since 2023-04-12 16:35:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryFeedback implements Serializable {
    private static final long serialVersionUID = 708830664657785057L;
    /**
     * id
     */
    private String feedbackId;
    /**
     * 学校id
     */
    @NotBlank(message = "schoolId不能为空")
    private String schoolId;
    /**
     * 用户id
     */
    @NotBlank(message = "userId不能为空")
    private String userId;
    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;
    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String context;
    /**
     * 处理情况
     */
    private Integer tag;
    /**
     * 发布日期
     */
    private Date date;


}

