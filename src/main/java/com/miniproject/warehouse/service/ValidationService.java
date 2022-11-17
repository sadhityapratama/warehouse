package com.miniproject.warehouse.service;

import com.miniproject.warehouse.exception.BadRequestException;
import com.miniproject.warehouse.repository.AssetRepository;
import com.miniproject.warehouse.repository.TransactionRepository;
import com.miniproject.warehouse.repository.UserRepository;
import com.miniproject.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    public void validateIfAssetExists(String assetBarcode) throws BadRequestException {
        if(!assetRepository.existsById(assetBarcode)){
            String message = String.format("Asset with barcode "+assetBarcode+" not exists!");
            throw new BadRequestException(message);
        }
    }

    public void validateDuplicateAsset(String assetBarcode)throws BadRequestException{
        if(assetRepository.existsByBarcode(assetBarcode)){
            String message = String.format("Asset with barcode "+assetBarcode+" is already exists!");
            throw new BadRequestException(message);
        }
    }

    public void validateIfTransactionExists(int transactionId) throws Exception{
        if(!transactionRepository.existsById(transactionId)){
            String message = String.format("Transaction with id %d not exists!", transactionId);
            throw new BadRequestException(message);
        }
    }

    public void validateIfWarehouseExists(int warehouseId) throws Exception{
        if(!warehouseRepository.existsById(warehouseId)){
            String message = String.format("Warehouse with id %d not exists!", warehouseId);
            throw new BadRequestException(message);
        }
    }



    public void validateDuplicateWarehouse(String warehouseName) throws Exception{
        if(warehouseRepository.existsByWarehouseName(warehouseName)){
            String message = String.format("Found duplicate warehouse with name %s", warehouseName);
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
