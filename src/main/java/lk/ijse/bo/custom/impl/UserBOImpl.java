package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveUser(UserDto dto) throws SQLException {
        return userDAO.save(new User(dto.getEmail(), dto.getPassword(), dto.getUserName()));
    }

    @Override
    public UserDto searchUser(String email) throws SQLException, ClassNotFoundException {
        User user = userDAO.search(email);
        if (user != null) {
            return new UserDto(user.getEmail(), user.getPassword(), user.getUserName());
        } else {
            return null;
        }
    }

    @Override
    public boolean updateUser(UserDto userDto) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(userDto.getEmail(), userDto.getPassword(), userDto.getUserName()));
    }
}
