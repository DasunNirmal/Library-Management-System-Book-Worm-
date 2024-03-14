package lk.ijse.dao;

import lk.ijse.dao.custom.impl.BranchDAOImpl;
import lk.ijse.dao.custom.impl.MembersDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER,MEMBERS,BRANCHES
    }

    public SuperDAO getDAO (DAOTypes types) {
        switch (types) {
            case USER:
                return new UserDAOImpl();
            case MEMBERS:
                return new MembersDAOImpl();
            case BRANCHES:
                return new BranchDAOImpl();
            default:
                return null;
        }
    }
}
