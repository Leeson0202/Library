package cool.leeson.library.entity.library.simple;

import cool.leeson.library.entity.library.School;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolSimple {
    private String schoolId;

    private String name;
    private List<LibrarySimple> librarySimpleList;

    public SchoolSimple(School school) {
        this.schoolId = school.getSchoolId();
        this.name = school.getName();
    }

}
