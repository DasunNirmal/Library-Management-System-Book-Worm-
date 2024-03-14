package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BooksBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BooksDAO;
import lk.ijse.dto.BooksDto;
import lk.ijse.entity.Books;

import java.sql.SQLException;
import java.util.ArrayList;
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
        List<BooksDto> books = new ArrayList<>();
        List<Books> list = booksDAO.getAll();
        for (Books book : list) {
            books.add(new BooksDto(book.getBookID(),
                    book.getTitle(), book.getGenre(), book.getAuthor(), book.getQty(), book.getAvailability(), book.getBranchID()));
        }
        return books;
    }

    @Override
    public String generateBookID() {
        return booksDAO.generateNextID();
    }
}
