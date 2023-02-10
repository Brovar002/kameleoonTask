package by.goncharov.kameleoonTask.service;


import by.goncharov.kameleoonTask.dto.UserDTO;
import by.goncharov.kameleoonTask.entity.User;

public interface UserService {
    User createUser(UserDTO userDTO);
    User findUserByEmail(String email);
}
