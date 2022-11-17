package com.miniproject.warehouse.service;

import com.fasterxml.uuid.Generators;
import com.miniproject.warehouse.dto.AssetReport;
import com.miniproject.warehouse.dto.AssetStockbyWarehouse;
import com.miniproject.warehouse.enums.TransactionTypeEnum;
import com.miniproject.warehouse.exception.BadRequestException;
import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.model.Stock;
import com.miniproject.warehouse.model.Transaction;
import com.miniproject.warehouse.model.Warehouse;
import com.miniproject.warehouse.repository.AssetRepository;
import com.miniproject.warehouse.repository.StockRepository;
import com.miniproject.warehouse.repository.TransactionRepository;
import com.miniproject.warehouse.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    WarehouseService warehouseService;
    @Autowired
    ValidationService validationService;

    public Asset getAsset(String barcode) throws Exception{
        validationService.validateIfAssetExists(barcode);
        return assetRepository.findAssetByBarcode(barcode);
    }

    public Asset addAsset(Asset asset) throws BadRequestException {
        UUID barcode = Generators.timeBasedGenerator().generate();
        asset.setBarcode(barcode.toString());
        validationService.validateDuplicateAsset(asset.getBarcode());

        return assetRepository.save(asset);
    }

    public Asset updateAsset(Asset asset) throws  Exception{
        validationService.validateIfAssetExists(asset.getBarcode());
        return  assetRepository.save(asset);
    }

    public void deleteAsset(String barcode)throws Exception{
        validationService.validateIfAssetExists(barcode);
        assetRepository.deleteById(barcode);
    }

    public List<AssetReport> getAssetReport() throws Exception {
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        List<AssetReport> assetReportList = new ArrayList<AssetReport>();
        for (Warehouse warehouse : warehouseList){
            List<AssetStockbyWarehouse> assetStockByWarehouseList = warehouseService.getAllAssetStock(warehouse.getId());
            List<Transaction> transactionCheckInList = transactionRepository.findTransactionByWarehouseId(warehouse.getId(), TransactionTypeEnum.CHECK_IN.name());
            List<Transaction> transactionCheckOutList = transactionRepository.findTransactionByWarehouseId(warehouse.getId(), TransactionTypeEnum.CHECK_OUT.name());

            for (AssetStockbyWarehouse assetStockbyWarehouse : assetStockByWarehouseList){
                AssetReport assetReport = new AssetReport();
                int totalCheckIn = 0;
                int totalCheckOut = 0;

                for(Transaction transaction : transactionCheckInList){
                    if(transaction.getAssetBarcode().equals(assetStockbyWarehouse.getAssets().getBarcode())){
                        totalCheckIn = totalCheckIn + transaction.getTransactionQuantity();
                    }
                }

                for(Transaction transaction : transactionCheckOutList){
                    if(transaction.getAssetBarcode().equals(assetStockbyWarehouse.getAssets().getBarcode())){
                        totalCheckOut = totalCheckOut + transaction.getTransactionQuantity();
                    }
                }

                assetReport.setAssetStockbyWarehouse(assetStockbyWarehouse);
                assetReport.setTransactionCheckIn(totalCheckIn);
                assetReport.setTransactionCheckOut(totalCheckOut);

                assetReportList.add(assetReport);
            }
        }




        return  assetReportList;
    }
}
