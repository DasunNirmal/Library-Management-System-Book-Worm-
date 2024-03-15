package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Transactions {
    @Id
    private String borrowingID;
    private String memberID;
    private String memberName;
    private String book;
    private String genre;
    private String borrowingDate;
    private String returningDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books books;
}
