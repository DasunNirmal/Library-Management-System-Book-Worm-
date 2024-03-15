package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.TransactionDto;

import java.sql.SQLException;
import java.util.List;

public interface TransactionBO extends SuperBO {

    boolean saveTransaction(TransactionDto dto) throws SQLException;

    boolean updateTransaction(TransactionDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteTransaction(String id) throws SQLException, ClassNotFoundException;

    TransactionDto searchTransaction(String id) throws SQLException, ClassNotFoundException;

    List<TransactionDto> getAllTransaction() throws SQLException, ClassNotFoundException;

    String generateTransactionID();

}
