package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchesTm {
    private String id;
    private String branchName;
    private String staff;
    private String manager;
    private String address;
}
