package com.miniproject.warehouse.dto;

import lombok.Data;

@Data
public class TransactionTotal {

    private String assetBarcode;
    private String transactionType;
    private int totalTransaction;
}
