package com.miniproject.warehouse.service;

import com.miniproject.warehouse.exception.BadRequestException;
import com.miniproject.warehouse.repository.UserRepository;
import com.miniproject.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private UserRepository userRepository;

    public void validateIfWarehouseExists(int warehouseId) throws Exception{
        if(!warehouseRepository.existsById(warehouseId)){
            String message = String.format("Warehouse with id %d not exists! ", warehouseId);
            throw new BadRequestException(message);
        }
    }
    public void validateDuplicateWarehouse(String warehouseName) throws Exception{
        if(warehouseRepository.existsByWarehouseName(warehouseName)){
            String message = String.format("Found duplicate warehouse with name %s ", warehouseName);
            throw new BadRequestException(message);
        }
    }

    public void validateIfUserExists(String username) throws Exception{
        if(!userRepository.existsByUsername(username)){
            String message = String.format("Username %s not exists!", username);
            throw new BadRequestException(message);
        }
    }

    public void validateDuplicateUser(String username) throws Exception{
        if(userRepository.existsByUsername(username)){
            String message = String.format("Found duplicate Username %s", username);
            throw new BadRequestException(message);
        }
    }

    public void validateIfUserExists(int userId) throws Exception{
        if(!userRepository.existsById(userId)){
            String message = String.format("User id %d not exists!", userId);
            throw new BadRequestException(message);
        }
    }
}
