package by.goncharov.kameleoontask.service.impl;

import by.goncharov.kameleoontask.dto.UserDTO;
import by.goncharov.kameleoontask.entity.User;
import by.goncharov.kameleoontask.exception.ResourceNotFoundException;
import by.goncharov.kameleoontask.repository.UserRepository;
import by.goncharov.kameleoontask.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);
        return mapUserToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user = userRepository.save(user);
        return mapUserToUserDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.delete(user);
    }

    private UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
