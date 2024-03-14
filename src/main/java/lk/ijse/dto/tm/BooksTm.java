package lk.ijse.dto.tm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BooksTm {
    private String bookID;
    private String title;
    private String genre;
    private String author;
    private String branchID;
    private String availability;
    private String qty;
}
