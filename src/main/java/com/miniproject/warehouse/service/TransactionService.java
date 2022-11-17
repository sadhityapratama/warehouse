package com.miniproject.warehouse.service;

import com.miniproject.warehouse.dto.AssetCheckInOut;
import com.miniproject.warehouse.enums.TransactionTypeEnum;
import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.model.Stock;
import com.miniproject.warehouse.model.Transaction;
import com.miniproject.warehouse.model.Warehouse;
import com.miniproject.warehouse.repository.AssetRepository;
import com.miniproject.warehouse.repository.StockRepository;
import com.miniproject.warehouse.repository.TransactionRepository;
import com.miniproject.warehouse.repository.WarehouseRepository;
import com.miniproject.warehouse.response.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public HttpResponse addTransaction(Transaction transaction){
        HttpResponse httpResponse = new HttpResponse();

        Asset asset = assetRepository.findAssetByBarcode(transaction.getAssetBarcode());
        Warehouse warehouse = warehouseRepository.findWarehouseById(transaction.getWarehouseId());


        if (asset != null && warehouse != null){
            Stock stock = stockRepository.findStockByAssetBarcodeAndWarehouseId(transaction.getAssetBarcode(), transaction.getWarehouseId());
            transaction.setTransactionDate(new Date());


            if(transaction.getTransactionType().equals(TransactionTypeEnum.CHECK_IN.name()) && stock == null){
                Stock addStock = new Stock();
                addStock.setAssetBarcode(transaction.getAssetBarcode());
                addStock.setWarehouseId(transaction.getWarehouseId());
                addStock.setStock(transaction.getTransactionQuantity());

                stockRepository.save(addStock);

                httpResponse.setObject(transactionRepository.save(transaction));
                httpResponse.setStatus(HttpStatus.OK.value());
                httpResponse.setMessage(HttpStatus.OK.name());
            } else if(transaction.getTransactionType().equals(TransactionTypeEnum.CHECK_IN.name()) && stock != null){
                stock.setStock(stock.getStock()+transaction.getTransactionQuantity());

                if(stock.getStock() > warehouse.getWarehouseCapacity()){
                    httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    httpResponse.setMessage("WAREHOUSE FULL, CANNOT ADD TRANSACTION!");
                } else {
                    stockRepository.save(stock);

                    httpResponse.setObject(transactionRepository.save(transaction));
                    httpResponse.setStatus(HttpStatus.OK.value());
                    httpResponse.setMessage(HttpStatus.OK.name());
                }


            } else if (transaction.getTransactionType().equals(TransactionTypeEnum.CHECK_OUT.name()) && stock == null){
                log.error("STOCK NOT FOUND, CANNOT ADD TRANSACTION!");

                httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                httpResponse.setMessage("STOCK NOT FOUND, CANNOT CHECK OUT TRANSACTION!");
            } else if(transaction.getTransactionType().equals(TransactionTypeEnum.CHECK_OUT.name()) && stock != null){
                if(stock.getStock() < transaction.getTransactionQuantity()){
                    log.error("NOT ENOUGH STOCK, CANNOT CHECK OUT TRANSACTION!");

                    httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    httpResponse.setMessage("NOT ENOUGH STOCK, CANNOT CHECK OUT TRANSACTION!");
                }else if(stock.getStock() >= transaction.getTransactionQuantity()){
                    stock.setStock(stock.getStock()- transaction.getTransactionQuantity());
                    stockRepository.save(stock);

                    httpResponse.setObject(transactionRepository.save(transaction));
                    httpResponse.setStatus(HttpStatus.OK.value());
                    httpResponse.setMessage(HttpStatus.OK.name());
                }
            }
        } else {
            log.error("ASSET OR WAREHOUSE NOT FOUND, CANNOT ADD TRANSACTION!");

            httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpResponse.setMessage("ASSET OR WAREHOUSE NOT FOUND, CANNOT ADD TRANSACTION!");
        }
        return httpResponse;
    }

    public List<AssetCheckInOut> getAssetCheckInOut(String transType, String assetBarcode){
        List<AssetCheckInOut> assetCheckInOutList = new ArrayList<AssetCheckInOut>();

        String transactionType = "";
        if(transType.equals("in")){
            transactionType = TransactionTypeEnum.CHECK_IN.name();
        } else if (transType.equals("out")){
            transactionType = TransactionTypeEnum.CHECK_OUT.name();
        } else {
            transactionType = TransactionTypeEnum.CHECK_IN.name();
        }

        Asset asset = assetRepository.findAssetByBarcode(assetBarcode);
        List<Transaction> transaction = transactionRepository.findTransactionByAssetBarcode(assetBarcode, transactionType);

        for (Transaction transactionObject : transaction) {
            AssetCheckInOut assetCheckInOut = new AssetCheckInOut();
            assetCheckInOut.setAssetInfo(asset.getAssetInformation());
            assetCheckInOut.setTransaction(transactionObject);

            assetCheckInOutList.add(assetCheckInOut);
        }

        return assetCheckInOutList;
    }
}
