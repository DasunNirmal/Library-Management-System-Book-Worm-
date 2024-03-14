package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User dto) throws SQLException {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextID() {
        return null;
    }

    @Override
    public boolean isValidUser(String name, String password) {
        Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
        Transaction transaction = session.beginTransaction();

        Query<User> query = session.createQuery("FROM User WHERE userName = :name AND password = :password", User.class);
        query.setParameter("name",name);
        query.setParameter("password",password);
        List<User> userList = query.getResultList();
        transaction.commit();
        session.close();

        /*if (!userList.isEmpty()) {
            return true;
        } else {
            return false;
        }*/

        /*Check if the userList is not empty, indicating that a user with the provided credentials exists in the database.*/
        return !userList.isEmpty();
    }
}
