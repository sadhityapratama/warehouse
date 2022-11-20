package com.miniproject.warehouse.controller;

import com.miniproject.warehouse.model.User;
import com.miniproject.warehouse.response.HttpResponse;
import com.miniproject.warehouse.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
//@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/auth/signin")
    public Principal user(Principal user){
        return user;
    }

//    @PostMapping("/login")
//    public boolean login(@RequestBody User user) {
//        return
//                user.getUsername().equals("user") && user.getPassword().equals("password");
//    }

    @PostMapping("/user/add")
    public HttpResponse createUser(@RequestBody User user) throws Exception{

        User userAdded = userService.createUser(user);

        // Set Http Response
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setMessage(HttpStatus.OK.name());
        httpResponse.setObject(userAdded);
        return httpResponse;
    }

    @PutMapping("/user/update")
    public HttpResponse updateUser(@RequestBody User user) throws Exception{

        User userUpdated = userService.updateUser(user);

        // Set Http Response
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setMessage(HttpStatus.OK.name());
        httpResponse.setObject(userUpdated);

        return httpResponse;
    }

    @DeleteMapping("/user/delete")
    public HttpResponse deleteUser(@RequestParam("id") int userId) throws Exception{

        User userDeleted = userService.deleteUser(userId);

        // Set Http Response
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setMessage(HttpStatus.OK.name());
        httpResponse.setObject(userDeleted);
        return httpResponse;
    }

    @GetMapping("/user/listall")
    public HttpResponse inquiryAllUser() throws Exception{

        List<User> userList = userService.inquiryAllUser();

        // Set Http Response
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setMessage(HttpStatus.OK.name());
        httpResponse.setObject(userList);

        return httpResponse;
    }

    @GetMapping("/user/list")
    public HttpResponse inquiryUser(@RequestParam("id") int userId) throws Exception{

        User user = userService.inquiryUser(userId);

        // Set Http Response
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setMessage(HttpStatus.OK.name());
        httpResponse.setObject(user);
        return httpResponse;
    }

//    @PostMapping("/login")
//    public HttpResponse login(@RequestBody User user) throws Exception{
//
//        String jwt = userService.login(user);
//
//        // Set Http Response
//        HttpResponse httpResponse = new HttpResponse();
//        httpResponse.setStatus(HttpStatus.OK.value());
//        httpResponse.setMessage(HttpStatus.OK.name());
//        httpResponse.setObject(jwt);
//        return httpResponse;
//    }
}
