package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Branches;

public interface BranchDAO extends CrudDAO<Branches> {
    String[] searchID(String id);
}
