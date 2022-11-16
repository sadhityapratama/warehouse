package com.miniproject.warehouse.controller;

import com.miniproject.warehouse.dto.AssetCheckInOut;
import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.model.Transaction;
import com.miniproject.warehouse.repository.AssetRepository;
import com.miniproject.warehouse.repository.TransactionRepository;
import com.miniproject.warehouse.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    TransactionService transactionService;

    @GetMapping("/listall")
    @ResponseStatus(HttpStatus.OK)
    List<Transaction> getAllTransaction(){
        return transactionRepository.findAll();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    Transaction getTransactionById(@PathVariable("id")int id){
        return transactionRepository.findTransactionById(id);
    }

    @GetMapping("/asset-check/")
    @ResponseStatus(HttpStatus.OK)
    List<AssetCheckInOut> getAssetCheckInOut(@RequestParam("transType")String transType, @RequestParam("barcode") String assetBarcode){
        return transactionService.getAssetCheckInOut(transType, assetBarcode);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    Transaction addTransaction(@RequestBody Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTransaction(@PathVariable("barcode") int id){

        transactionRepository.deleteById(id);
        return "DELETED SUCCESSFULLY";
    }
}
