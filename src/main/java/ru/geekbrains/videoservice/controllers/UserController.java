package ru.geekbrains.videoservice.controllers;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.videoservice.entities.User;
import ru.geekbrains.videoservice.services.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/addUsers")
    public List<User> addUser(@RequestBody List<User> users) {
        return userService.saveUsers(users);
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/userById/{id}")
    public User findUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/{name}")
    public User findUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @GetMapping("/user/{secondName}")
    public User findUserBySecondName(@PathVariable String secondName){
        return userService.getUserBySecondName(secondName);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

}