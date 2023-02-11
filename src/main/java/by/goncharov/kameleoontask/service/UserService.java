package by.goncharov.kameleoontask.service;


import by.goncharov.kameleoontask.dto.UserDTO;
import by.goncharov.kameleoontask.entity.User;

public interface UserService {
    User createUser(UserDTO userDTO);
    User findUserByEmail(String email);
}
