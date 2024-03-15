package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.MembersDAO;
import lk.ijse.entity.Branches;
import lk.ijse.entity.Member;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class MembersDAOImpl implements MembersDAO {
    @Override
    public boolean save(Member dto) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Member dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("DELETE FROM Member WHERE id ='"+id+"'", Member.class).executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Member search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Member entity = null;
        Query<Member> query = session.createQuery("FROM Member WHERE phoneNumber = :phoneNumber", Member.class);
        query.setParameter("phoneNumber",id);
        List<Member> membersList = query.getResultList();

        if (!membersList.isEmpty()) {
            entity = membersList.get(0);
        }
        transaction.commit();
        session.close();
        return entity;
    }

    @Override
    public List<Member> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        List<Member> list = session.createNativeQuery("SELECT * FROM Member", Member.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String generateNextID() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createQuery("SELECT id FROM Member ORDER BY id DESC LIMIT 1", String.class);
        query.setMaxResults(1);
        String currentMemberID = query.uniqueResult();

        transaction.commit();
        session.close();

        if (currentMemberID != null) {
            return splitMemberID(currentMemberID);
        } else {
            return splitMemberID(null);
        }
    }

    private String splitMemberID(String currentMemberID) {
        if (currentMemberID != null) {
            String[] split = currentMemberID.split("M");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("M%03d", id);
        } else {
            return "M001";
        }
    }

    @Override
    public String[] searchPhoneNumber(String phoneNumber) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createQuery("SELECT phoneNumber FROM Member WHERE phoneNumber LIKE :phoneNumber", String.class);
        query.setParameter("phoneNumber", "%" + phoneNumber + "%");

        List<String> branches = query.list();

        transaction.commit();
        session.close();

        return branches.toArray(new String[0]);
    }
}
