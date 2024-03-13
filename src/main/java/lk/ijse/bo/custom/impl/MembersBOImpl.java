package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MembersBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.MembersDAO;
import lk.ijse.dto.MemberDto;
import lk.ijse.entity.Member;

import java.sql.SQLException;

public class MembersBOImpl implements MembersBO {

    MembersDAO membersDAO = (MembersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERS);

    @Override
    public boolean saveMember(MemberDto dto) throws SQLException {
        return membersDAO.save(new Member(dto.getId(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getAddress()));
    }

    @Override
    public boolean updateMember(MemberDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public MemberDto searchMember(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextMemberID() {
        return membersDAO.generateMemberID();
    }
}
