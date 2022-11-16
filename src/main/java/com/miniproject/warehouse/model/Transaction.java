package com.miniproject.warehouse.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    @Column(name = "asset_barcode", nullable = false, updatable = false)
    private String assetBarcode;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

    @Column(name = "transaction_quantity", nullable = false)
    private int transactionQuantity;

    @Column(name = "warehouse_id", nullable = false, updatable = false)
    private int warehouseId;


}
