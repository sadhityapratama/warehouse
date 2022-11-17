package com.miniproject.warehouse.service;

import com.miniproject.warehouse.dto.AssetReport;
import com.miniproject.warehouse.dto.TransactionTotal;
import com.miniproject.warehouse.enums.TransactionTypeEnum;
import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.model.Stock;
import com.miniproject.warehouse.model.Transaction;
import com.miniproject.warehouse.repository.AssetRepository;
import com.miniproject.warehouse.repository.StockRepository;
import com.miniproject.warehouse.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    StockRepository stockRepository;

}
