package com.miniproject.warehouse.dto;

import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.model.Stock;
import com.miniproject.warehouse.model.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class AssetReport {
    private Asset asset;
    private List<Stock> stock;
    private int transactionCheckIn;
    private int transactionCheckOut;


}
