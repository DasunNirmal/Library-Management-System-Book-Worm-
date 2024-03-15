package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.OverdueDto;
import lk.ijse.entity.Transactions;

import java.util.List;

public interface TransactionDAO extends CrudDAO<Transactions> {
    List<OverdueDto> getAllOverDueBooks();

    String getTotalOverdueBooks();

    String getTotalBorrowedBooks();
}
