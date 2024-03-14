package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.MembersDAO;
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
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Member search(String id) throws SQLException, ClassNotFoundException {
        return null;
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
    public String generateMemberID() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createQuery("SELECT id FROM Member ORDER BY id DESC LIMIT 1", String.class);
        query.setMaxResults(1);
        String currentComplainID = query.uniqueResult();

        transaction.commit();
        session.close();

        if (currentComplainID != null) {
            return splitComplainID(currentComplainID);
        } else {
            return splitComplainID(null);
        }
    }

    private String splitComplainID(String currentComplainID) {
        if (currentComplainID != null) {
            String[] split = currentComplainID.split("M");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("M%03d", id);
        } else {
            return "M001";
        }
    }
}
