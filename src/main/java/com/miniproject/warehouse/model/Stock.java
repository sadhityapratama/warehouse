package com.miniproject.warehouse.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false )
    private int id;

    @Column(name = "asset_barcode", updatable = false, nullable = false)
    private String assetBarcode;

    @Column(name = "warehouse_id", updatable = false, nullable = false)
    private int warehouseId;

    @Column(name = "stock", nullable = false)
    private int stock;

}
