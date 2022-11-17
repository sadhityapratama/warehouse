package com.miniproject.warehouse.service;

import com.miniproject.warehouse.exception.BadRequestException;
import com.miniproject.warehouse.model.User;
import com.miniproject.warehouse.repository.UserRepository;
import com.miniproject.warehouse.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private JwtUtil jwtUtil;

    public String login(User user) throws Exception {
        log.info("[LOGIN] User Login {}", user.getUsername());

        /* Validate Login */
        validationService.validateIfUserExists(user.getUsername());

        /* Authentication */
        User userOnDatabase = userRepository.findByUsername(user.getUsername());
        if (!user.getPassword().equals(userOnDatabase.getPassword())){
            log.error("Invalid Credential!");
            throw new BadRequestException("Invalid Credential!");
        }

        /* Generate JWT Token */
        String jwt = jwtUtil.generateToken(user.getUsername(),userOnDatabase.getRole());

        return jwt;
    }

    public User createUser(User user) throws Exception{
        log.info("[INSERT] Create new User with username {}", user.getUsername());
        /**
         * Validation Step
         */
        validationService.validateDuplicateUser(user.getUsername());

        return userRepository.save(user);
    }

    public User updateUser(User user) throws Exception{
        log.info("[UPDATE] Update User with username {}", user.getUsername());
        /**
         * Validation Step
         */
        validationService.validateIfUserExists(user.getId());

        return userRepository.save(user);
    }

    public User deleteUser(int userId) throws Exception{
        log.info("[DELETE] Delete User with id {}", userId);
        /**
         * Validation Step
         */
        validationService.validateIfUserExists(userId);

        return userRepository.deleteById(userId);
    }

    public List<User> inquiryAllUser() throws Exception{
        log.info("[INQUIRY] Inquiry All User");
        return userRepository.findAll();
    }

    public User inquiryUser(int userId) throws Exception{
        log.info("[INQUIRY] Inquiry userId {}", userId);
        /**
         * Validation Step
         */
        validationService.validateIfUserExists(userId);

        return userRepository.findUserById(userId);
    }
}
