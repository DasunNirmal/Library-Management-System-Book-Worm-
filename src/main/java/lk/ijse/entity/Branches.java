package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Branches {
    @Id
    private String id;
    private String branchName;
    private String staff;
    private String manager;
    private String address;

    @OneToMany(mappedBy = "branches")
    private List<Books> books;
}
