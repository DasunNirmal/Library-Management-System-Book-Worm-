package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.entity.Transactions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    @Override
    public boolean save(Transactions dto) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Transactions dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("DELETE FROM Transactions WHERE borrowingID ='"+id+"'", Transactions.class).executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Transactions search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Transactions> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        List<Transactions> list = session.createNativeQuery("SELECT * FROM Transactions", Transactions.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String generateNextID() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createQuery("SELECT id FROM Transactions ORDER BY id DESC LIMIT 1", String.class);
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

    @Override
    public List<Transactions> getAllOverDueBooks() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();
        List<Transactions> list = session.createNativeQuery("SELECT * FROM Transactions WHERE returningDate < CURRENT_DATE", Transactions.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String getTotalOverdueBooks() {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Query<Long> query = session.createQuery("SELECT count(*) FROM Transactions ", Long.class);
        Long count = query.uniqueResult();
        String overdueCount = String.valueOf(count);

        transaction.commit();
        session.close();

        return overdueCount;
    }
}
