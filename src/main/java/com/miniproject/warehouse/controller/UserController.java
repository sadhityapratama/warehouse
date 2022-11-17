package com.miniproject.warehouse.controller;

import com.miniproject.warehouse.model.User;
import com.miniproject.warehouse.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public User createUser(@RequestBody User user) throws Exception{

        return userService.createUser(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) throws Exception{

        return userService.updateUser(user);
    }

    @DeleteMapping("/delete")
    public User deleteUser(@RequestParam("id") int userId) throws Exception{

        return userService.deleteUser(userId);
    }

    @GetMapping("/listall")
    public List<User> inquiryAllUser() throws Exception{

        return userService.inquiryAllUser();
    }

    @GetMapping("/list")
    public User inquiryUser(@RequestParam("id") int userId) throws Exception{

        return userService.inquiryUser(userId);
    }
}
