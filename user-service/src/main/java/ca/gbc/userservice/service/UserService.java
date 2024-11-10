package ca.gbc.userservice.service;

import ca.gbc.userservice.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    List<User> getUsersByRole(String role);
    List<User> getUsersByUserType(String userType);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
