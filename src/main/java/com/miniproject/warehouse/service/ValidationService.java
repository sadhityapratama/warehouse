package com.miniproject.warehouse.service;

import com.miniproject.warehouse.exception.BadRequestException;
import com.miniproject.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public void validateIfWarehouseExists(int warehouseId) throws Exception{
        if(!warehouseRepository.existsById(warehouseId)){
            String message = String.format("Warehouse with id %d not exists! ", warehouseId);
            throw new BadRequestException(message);
        }
    }
}
