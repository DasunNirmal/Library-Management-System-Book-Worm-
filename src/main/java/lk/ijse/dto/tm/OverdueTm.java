package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OverdueTm {
    private String borrowingID;
    private String memberID;
    private String memberName;
    private String bookName;
    private String genre;
    private String bDate;
    private String returningDate;
    private String bookID;
}
