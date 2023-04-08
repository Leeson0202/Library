package cool.leeson.library.entity.library.simple;

import cool.leeson.library.entity.library.LibrarySeat;
import cool.leeson.library.entity.library.LibraryTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableSimple {
    private String tableId;
    private String name;

    public TableSimple(LibraryTable libraryTable) {
        this.tableId = libraryTable.getTableId();
        this.name = libraryTable.getName();
    }
}
