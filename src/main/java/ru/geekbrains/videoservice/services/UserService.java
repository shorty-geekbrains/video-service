package ru.geekbrains.videoservice.services;

import org.springframework.stereotype.Service;
import ru.geekbrains.videoservice.entities.User;
import ru.geekbrains.videoservice.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User getUserBySecondName(String userSecondName) {
        return userRepository.findByUserSecondName(userSecondName);
    }

    public String deleteUser(long id) {
        userRepository.deleteById(id);
        return "User removed! " + id;
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);

        existingUser.setUserName(user.getUserName());
        existingUser.setUserSecondName(user.getUserSecondName());
        existingUser.setAge(user.getAge());
        existingUser.setUserPassword(user.getUserPassword());
        existingUser.setSex(user.isSex());

        return userRepository.save(existingUser);
    }

}
