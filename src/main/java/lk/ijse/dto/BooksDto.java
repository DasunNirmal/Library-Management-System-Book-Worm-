package lk.ijse.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BooksDto {
    private String bookID;
    private String title;
    private String genre;
    private String author;
    private String qty;
    private String availability;
    private String branchID;
}
