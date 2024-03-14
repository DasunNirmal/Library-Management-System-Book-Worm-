package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BooksDAO;
import lk.ijse.entity.Books;
import lk.ijse.entity.Member;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class BooksDAOImpl implements BooksDAO {
    @Override
    public boolean save(Books dto) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Books dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Books search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Books> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        List<Books> list = session.createNativeQuery("SELECT * FROM Books", Books.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String generateNextID() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createQuery("SELECT id FROM Books ORDER BY id DESC LIMIT 1", String.class);
        query.setMaxResults(1);
        String currentBookID = query.uniqueResult();

        transaction.commit();
        session.close();

        if (currentBookID != null) {
            return splitBookID(currentBookID);
        } else {
            return splitBookID(null);
        }
    }

    private String splitBookID(String currentBookID) {
        if (currentBookID != null) {
            String[] split = currentBookID.split("B");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("B%03d", id);
        } else {
            return "B001";
        }
    }
}
