package lk.ijse.dto;

import lk.ijse.entity.Books;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDto {
    private String borrowingID;
    private String memberID;
    private String memberName;
    private String book;
    private String genre;
    private String borrowingDate;
    private String returningDate;
    private Books books;
}
