package lk.ijse.dao;

import java.sql.SQLException;

public interface CrudDAO<T> extends SuperDAO {

    boolean save(T dto) throws SQLException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    T search(String id) throws SQLException, ClassNotFoundException;
}
