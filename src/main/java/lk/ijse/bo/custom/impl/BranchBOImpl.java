package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.entity.Branches;

import java.sql.SQLException;
import java.util.List;

public class BranchBOImpl implements BranchBO {

    BranchDAO branchDAO = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BRANCHES);

    @Override
    public boolean saveBranch(Branches dto) throws SQLException {
        return false;
    }

    @Override
    public boolean updateBranch(Branches dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteBranch(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Branches searchBranch(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Branches> getAllBranches() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateBranchID() {
        return branchDAO.generateNextID();
    }
}
