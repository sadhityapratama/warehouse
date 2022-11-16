package com.miniproject.warehouse.dto;

import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.model.Transaction;
import lombok.Data;

@Data
public class AssetCheckInOut {

    private Transaction transaction;
    private String assetInfo;

}
