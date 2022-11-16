package com.miniproject.warehouse.model;

import javax.persistence.*;

@Entity
@Table(name = "")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "warehouse_name", nullable = false)
    private String warehouseName;

    @Column(name = "warehouse_location", nullable = false)
    private String warehouseLocation;

    @Column(name = "warehouse_capacity", nullable = false)
    private double warehouseCapacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public double getWarehouseCapacity() {
        return warehouseCapacity;
    }

    public void setWarehouseCapacity(double warehouseCapacity) {
        this.warehouseCapacity = warehouseCapacity;
    }
}
