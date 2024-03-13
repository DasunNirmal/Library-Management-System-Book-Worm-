package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDto;

import java.sql.SQLException;

public interface UserBO extends SuperBO {

    boolean saveUser(UserDto dto) throws SQLException;
}
