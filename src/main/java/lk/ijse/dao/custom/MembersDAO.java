package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Member;

public interface MembersDAO extends CrudDAO<Member> {

    String[] searchPhoneNumber(String phoneNumber);
}
