package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.entity.Branches;

import java.sql.SQLException;
import java.util.List;

public interface BranchBO extends SuperBO {

    boolean saveBranch(Branches dto) throws SQLException;

    boolean updateBranch(Branches dto) throws SQLException, ClassNotFoundException;

    boolean deleteBranch(String id) throws SQLException, ClassNotFoundException;

    Branches searchBranch(String id) throws SQLException, ClassNotFoundException;

    List<Branches> getAllBranches() throws SQLException, ClassNotFoundException;

    String generateBranchID();
}
