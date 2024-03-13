package lk.ijse.bo;

import lk.ijse.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory () {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        USER
    }

    public SuperBO grtBo(BOTypes boTypes) {
        switch (boTypes) {
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
