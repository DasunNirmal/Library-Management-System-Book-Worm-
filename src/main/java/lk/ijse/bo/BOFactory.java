package lk.ijse.bo;

import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.bo.custom.impl.BranchBOImpl;
import lk.ijse.bo.custom.impl.MembersBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory () {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        USER,MEMBERS,BRANCHES,BOOKS
    }

    public SuperBO grtBo(BOTypes boTypes) {

        switch (boTypes) {
            case USER:
                return new UserBOImpl();
            case MEMBERS:
                return new MembersBOImpl();
            case BRANCHES:
                return new BranchBOImpl();
            case BOOKS:
                return new BookBOImpl();
            default:
                return null;
        }
    }
}
