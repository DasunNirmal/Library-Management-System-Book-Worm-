package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Transactions;

import java.util.List;

public interface TransactionDAO extends CrudDAO<Transactions> {
    List<Transactions> getAllOverDueBooks();

    String getTotalOverdueBooks();
}
