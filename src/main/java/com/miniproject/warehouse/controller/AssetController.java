package com.miniproject.warehouse.controller;

import com.fasterxml.uuid.Generators;
import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.model.Stock;
import com.miniproject.warehouse.model.Transaction;
import com.miniproject.warehouse.model.Warehouse;
import com.miniproject.warehouse.repository.AssetRepository;
import com.miniproject.warehouse.repository.StockRepository;
import com.miniproject.warehouse.repository.TransactionRepository;
import com.miniproject.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("asset")
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StockRepository stockRepository;

    @GetMapping("/listall")
    @ResponseStatus(HttpStatus.OK)
    List<Asset> getAllAsset(){
        return assetRepository.findAll();
    }

    @GetMapping("/get/{barcode}")
    @ResponseStatus(HttpStatus.OK)
    Asset getAssetByBarcode(@PathVariable("barcode")String barcode){
        return assetRepository.findAssetByBarcode(barcode);
    }

    @PostMapping("/add/{warehouseId}")
    @ResponseStatus(HttpStatus.OK)
    Asset addAsset(@RequestBody Asset asset,@PathVariable("warehouseId") int warehouseId){

        UUID barcode = Generators.timeBasedGenerator().generate();
        asset.setBarcode(barcode.toString());

        Warehouse warehouse = warehouseRepository.findWarehouseById(warehouseId);

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(new Date());
        transaction.setTransactionQuantity(1);
        transaction.setWarehouseId(warehouse.getId());
        transaction.setTransactionType("CHECK_IN");

        Stock stock = stockRepository.findStockByAssetBarcode(asset.getBarcode());
        if (stock == null){
            stock.setAssetBarcode(asset.getBarcode());
            stock.setWarehouseId(warehouseId);
            stock.setStock(1);
        } else {
            stock.setStock(stock.getStock()+1);
        }



        stockRepository.save(stock);

        transactionRepository.save(transaction);

        return assetRepository.save(asset);
    }

    @PutMapping("/update/")
    @ResponseStatus(HttpStatus.OK)
    Asset updateAsset(@RequestBody Asset asset){
        return assetRepository.save(asset);
    }

    @DeleteMapping("/delete/{barcode}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAsset(@PathVariable("barcode") String barcode){

        assetRepository.deleteById(barcode);


        return "DELETED SUCCESSFULLY";
    }



}
