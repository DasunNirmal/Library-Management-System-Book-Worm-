package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.dto.TransactionDto;

import java.sql.SQLException;
import java.util.List;

public class TransactionBOImpl implements TransactionBO {

    TransactionDAO transactionDAO = (TransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TRANSACTION);

    @Override
    public boolean saveTransaction(TransactionDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean updateTransaction(TransactionDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteTransaction(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public TransactionDto searchTransaction(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<TransactionDto> getAllTransaction() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateTransactionID() {
        return transactionDAO.generateNextID();
    }
}
