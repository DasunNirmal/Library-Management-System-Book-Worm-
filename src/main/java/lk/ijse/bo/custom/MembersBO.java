package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.MemberDto;


import java.sql.SQLException;

public interface MembersBO extends SuperBO {

    boolean saveMember(MemberDto dto) throws SQLException;

    boolean updateMember(MemberDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteMember(String id) throws SQLException, ClassNotFoundException;

    MemberDto searchMember(String id) throws SQLException, ClassNotFoundException;

    String generateNextMemberID();
}
