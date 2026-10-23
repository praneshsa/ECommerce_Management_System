package DAO;

import java.util.List;
import DTO.User;

public interface UserDAO {

    boolean addUser(User user);

    List<User> getAllUsers();

    User getUserById(int userId);

    boolean updateUser(User user);

    boolean deleteUser(int userId);

    User login(String email, String password);
}