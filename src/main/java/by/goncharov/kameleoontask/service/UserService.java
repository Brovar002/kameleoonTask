package by.goncharov.kameleoontask.service;


import by.goncharov.kameleoontask.dto.UserDTO;
import by.goncharov.kameleoontask.entity.User;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    User findUserByEmail(String email);
}
