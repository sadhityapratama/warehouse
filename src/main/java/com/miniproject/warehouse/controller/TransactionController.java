package com.miniproject.warehouse.controller;

import com.miniproject.warehouse.dto.AssetCheckInOut;
import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.model.Transaction;
import com.miniproject.warehouse.repository.AssetRepository;
import com.miniproject.warehouse.repository.TransactionRepository;
import com.miniproject.warehouse.response.HttpResponse;
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
    public HttpResponse getAllTransaction(){
        HttpResponse httpResponse = new HttpResponse();
        List<Transaction> transactionList = transactionRepository.findAll();
        httpResponse.setObject(transactionList);

        if (transactionList.isEmpty()){
            httpResponse.setMessage(HttpStatus.NO_CONTENT.name());
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
        }else {
            httpResponse.setMessage(HttpStatus.OK.name());
            httpResponse.setStatus(HttpStatus.OK.value());
        }
        return httpResponse;
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponse getTransactionById(@PathVariable("id")int id) throws Exception {
        HttpResponse httpResponse = new HttpResponse();
        Transaction transaction = transactionService.getTransaction(id);
        httpResponse.setObject(transaction);
        httpResponse.setMessage(HttpStatus.OK.name());
        httpResponse.setStatus(HttpStatus.OK.value());
        return httpResponse;
    }

    @GetMapping("/asset-check/")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponse getAssetCheckInOut(@RequestParam("transType")String transType, @RequestParam("barcode") String assetBarcode){
        HttpResponse httpResponse = new HttpResponse();
        List<AssetCheckInOut> assetCheckInOutList = transactionService.getAssetCheckInOut(transType, assetBarcode);
        httpResponse.setObject(assetCheckInOutList);
        if (assetCheckInOutList.isEmpty()){
            httpResponse.setMessage(HttpStatus.NO_CONTENT.name());
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
        }else {
            httpResponse.setMessage(HttpStatus.OK.name());
            httpResponse.setStatus(HttpStatus.OK.value());
        }

        return httpResponse;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponse addTransaction(@RequestBody Transaction transaction) throws Exception {
        return transactionService.addTransaction(transaction);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponse deleteTransaction(@PathVariable("barcode") int id) throws Exception {
        transactionService.deleteTransaction(id);
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setMessage(HttpStatus.OK.name());


        return httpResponse;
    }
}
