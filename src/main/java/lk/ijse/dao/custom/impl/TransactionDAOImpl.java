package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.entity.Transaction;

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
        return null;
    }
}
