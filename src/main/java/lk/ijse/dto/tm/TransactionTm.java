package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionTm {
    private String borrowingID;
    private String memberID;
    private String memberName;
    private String book;
    private String genre;
    private String borrowingDate;
    private String returningDate;
    private String bookID;
}
