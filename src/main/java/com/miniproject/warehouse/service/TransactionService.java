package com.miniproject.warehouse.service;

import com.miniproject.warehouse.dto.AssetCheckInOut;
import com.miniproject.warehouse.enums.TransactionTypeEnum;
import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.model.Transaction;
import com.miniproject.warehouse.repository.AssetRepository;
import com.miniproject.warehouse.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private TransactionRepository transactionRepository;

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
