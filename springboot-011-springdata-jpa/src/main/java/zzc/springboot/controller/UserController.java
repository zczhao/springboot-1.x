package zzc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zzc.springboot.entity.User;
import zzc.springboot.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userRepository.findOne(id);
    }

    @GetMapping("/user")
    public User insertUser(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }

}
