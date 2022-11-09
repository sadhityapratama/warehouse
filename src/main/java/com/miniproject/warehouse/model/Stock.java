package com.miniproject.warehouse.model;

import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id", updatable = false, nullable = false )
    private int id;

    @Column(name = "asset_barcode", updatable = false, nullable = false)
    private String assetBarcode;

    @Column(name = "warehouse_id", updatable = false, nullable = false)
    private int warehouseId;

    @Column(name = "stock", nullable = false)
    private int stock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssetBarcode() {
        return assetBarcode;
    }

    public void setAssetBarcode(String assetBarcode) {
        this.assetBarcode = assetBarcode;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
