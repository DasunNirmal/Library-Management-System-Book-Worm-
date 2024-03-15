package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.dto.TransactionDto;
import lk.ijse.entity.Transactions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionBOImpl implements TransactionBO {

    TransactionDAO transactionDAO = (TransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TRANSACTION);

    @Override
    public boolean saveTransaction(TransactionDto dto) throws SQLException {
        return transactionDAO.save(new Transactions(dto.getBorrowingID(),dto.getMemberID(),dto.getMemberName(),
                dto.getBook(),dto.getGenre(),
                dto.getBorrowingDate(),dto.getReturningDate(),dto.getBooks()));
    }

    @Override
    public boolean updateTransaction(TransactionDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteTransaction(String id) throws SQLException, ClassNotFoundException {
        return transactionDAO.delete(id);
    }

    @Override
    public List<TransactionDto> getAllTransaction() throws SQLException, ClassNotFoundException {
        List<TransactionDto> transactions = new ArrayList<>();
        List<Transactions> list = transactionDAO.getAll();
        for (Transactions transaction : list) {
            transactions.add(new TransactionDto(
                    transaction.getBorrowingID(),transaction.getMemberID(),transaction.getMemberName(),
                    transaction.getBook(),transaction.getGenre(),
                    transaction.getBorrowingDate(),transaction.getReturningDate(),transaction.getBooks()
            ));
        }
        return transactions;
    }

    @Override
    public String generateTransactionID() {
        return transactionDAO.generateNextID();
    }

    @Override
    public List<TransactionDto> getAllOverDueBooks() {
        List<TransactionDto> transactions = new ArrayList<>();
        List<Transactions> list = transactionDAO.getAllOverDueBooks();
        for (Transactions transaction : list) {
            transactions.add(new TransactionDto(
                    transaction.getBorrowingID(),transaction.getMemberID(),transaction.getMemberName(),
                    transaction.getBook(),transaction.getGenre(),
                    transaction.getBorrowingDate(),transaction.getReturningDate(),transaction.getBooks()
            ));
        }
        return transactions;
    }
}
