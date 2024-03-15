package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory () {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        USER,MEMBERS,BRANCHES,BOOKS,TRANSACTION
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
            case TRANSACTION:
                return new TransactionBOImpl();
            default:
                return null;
        }
    }
}
