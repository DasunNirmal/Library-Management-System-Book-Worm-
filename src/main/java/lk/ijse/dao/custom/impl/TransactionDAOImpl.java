package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.entity.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    @Override
    public boolean save(Transaction dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Transaction dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Transaction search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Transaction> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextID() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        org.hibernate.Transaction transaction = session.beginTransaction();

        Query<String> query = session.createQuery("SELECT id FROM Transaction ORDER BY id DESC LIMIT 1", String.class);
        query.setMaxResults(1);
        String currentTransactionID = query.uniqueResult();

        transaction.commit();
        session.close();

        if (currentTransactionID != null) {
            return splitBookID(currentTransactionID);
        } else {
            return splitBookID(null);
        }
    }

    private String splitBookID(String currentTransactionID) {
        if (currentTransactionID != null) {
            String[] split = currentTransactionID.split("T");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("T%03d", id);
        } else {
            return "T001";
        }
    }
}
