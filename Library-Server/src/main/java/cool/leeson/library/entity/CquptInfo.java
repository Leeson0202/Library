package cool.leeson.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CquptInfo {
    private String userId;
    private String role;
    private String name;
    private String cqupt_id;
    private String student_id;
    private String grade;
    private String classs;
    private String academy_name;
    private String profession_name;
    private String gender;
    private String counselor_name;
    private String counselor_cqupt_id;
}
