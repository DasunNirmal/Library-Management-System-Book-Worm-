package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BranchesDto;

import java.sql.SQLException;
import java.util.List;

public interface BranchBO extends SuperBO {

    boolean saveBranch(BranchesDto dto) throws SQLException;

    boolean updateBranch(BranchesDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteBranch(String id) throws SQLException, ClassNotFoundException;

    BranchesDto searchBranch(String id) throws SQLException, ClassNotFoundException;

    List<BranchesDto> getAllBranches() throws SQLException, ClassNotFoundException;

    String generateBranchID();

    String[] searchBranchID(String id);
}
