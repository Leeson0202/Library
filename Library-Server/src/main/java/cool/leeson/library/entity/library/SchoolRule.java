package cool.leeson.library.entity.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (SchoolRule)实体类
 *
 * @author Leeson0202
 * @since 2023-03-11 20:18:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolRule implements Serializable {
    private static final long serialVersionUID = -36179764281798462L;
    /**
     * 学校id
     */
    private String schoolId;
    /**
     * 内容
     */
    private String context;


}

