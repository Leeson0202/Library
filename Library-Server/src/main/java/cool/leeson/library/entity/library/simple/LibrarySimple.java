package cool.leeson.library.entity.library.simple;

import cool.leeson.library.entity.library.Library;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibrarySimple {
    private String libraryId;
    private String name;
    private List<RoomSimple> roomSimpleList;

    public LibrarySimple(Library library) {
        this.libraryId = library.getLibraryId();
        this.name = library.getName();
    }

}
