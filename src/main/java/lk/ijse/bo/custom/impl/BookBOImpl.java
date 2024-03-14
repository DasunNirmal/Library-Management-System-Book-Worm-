package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BooksBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BooksDAO;
import lk.ijse.dto.BooksDto;
import lk.ijse.entity.Books;

import java.sql.SQLException;
import java.util.List;

public class BookBOImpl implements BooksBO {

    BooksDAO booksDAO = (BooksDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOKS);

    @Override
    public boolean saveBooks(BooksDto dto) throws SQLException {
        return booksDAO.save(new Books(
                dto.getBookID(),
                dto.getTitle(), dto.getGenre(), dto.getAuthor(), dto.getQty(), dto.getAvailability(), dto.getBranchID()));
    }

    @Override
    public boolean updateBooks(BooksDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteBooks(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public BooksDto searchBooks(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<BooksDto> getAllBooks() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateBookID() {
        return booksDAO.generateNextID();
    }
}
