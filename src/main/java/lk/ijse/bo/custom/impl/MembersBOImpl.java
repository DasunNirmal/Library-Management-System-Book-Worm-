package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MembersBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.MembersDAO;
import lk.ijse.dto.MemberDto;
import lk.ijse.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembersBOImpl implements MembersBO {

    MembersDAO membersDAO = (MembersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERS);

    @Override
    public boolean saveMember(MemberDto dto) throws SQLException {
        return membersDAO.save(new Member(dto.getId(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getAddress()));
    }

    @Override
    public boolean updateMember(MemberDto dto) throws SQLException, ClassNotFoundException {
        return membersDAO.update(new Member(dto.getId(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getAddress()));
    }

    @Override
    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException {
        return membersDAO.delete(id);
    }

    @Override
    public MemberDto searchMember(String phoneNumber) throws SQLException, ClassNotFoundException {
        Member member = membersDAO.search(phoneNumber);
        if (member != null) {
            return new MemberDto(member.getId(), member.getName(), member.getPhoneNumber(), member.getEmail(), member.getAddress());
        } else {
            return null;
        }
    }

    @Override
    public String generateNextMemberID() {
        return membersDAO.generateNextID();
    }

    @Override
    public List<MemberDto> getAllMembers() throws SQLException, ClassNotFoundException {
        List<MemberDto> members = new ArrayList<>();
        List<Member> list = membersDAO.getAll();
        for (Member member : list) {
            members.add(new MemberDto(member.getId(),member.getName(),member.getPhoneNumber(),member.getEmail(),member.getAddress()));
        }
        return members;
    }

    @Override
    public String[] searchMemberPhoneNumber(String phoneNumber) {
        return membersDAO.searchPhoneNumber(phoneNumber);
    }
}
