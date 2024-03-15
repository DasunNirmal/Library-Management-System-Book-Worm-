package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Books;

public interface BooksDAO extends CrudDAO<Books> {
    Books searchByID(String searchInput);

    String[] getBookByID(String id);

    String[] getBookByName(String name);
}
