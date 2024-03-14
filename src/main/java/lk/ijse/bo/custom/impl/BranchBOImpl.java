package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.dto.BranchesDto;
import lk.ijse.entity.Branches;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchBOImpl implements BranchBO {

    BranchDAO branchDAO = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BRANCHES);

    @Override
    public boolean saveBranch(BranchesDto dto) throws SQLException {
        return branchDAO.save(new Branches(dto.getId(),dto.getBranchName(),dto.getStaff(),dto.getManager(),dto.getAddress()));
    }

    @Override
    public boolean updateBranch(BranchesDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteBranch(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public BranchesDto searchBranch(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<BranchesDto> getAllBranches() throws SQLException, ClassNotFoundException {
        List<BranchesDto> branches = new ArrayList<>();
        List<Branches> list = branchDAO.getAll();
        for (Branches branch : list) {
            branches.add(new BranchesDto(
                    branch.getId(),branch.getBranchName(),branch.getStaff(),branch.getManager(),branch.getAddress()));
        }
        return branches;
    }

    @Override
    public String generateBranchID() {
        return branchDAO.generateNextID();
    }
}
