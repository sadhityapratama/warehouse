package com.miniproject.warehouse.dto;

import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.model.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class AssetStockbyWarehouse {

    private Warehouse warehouse;
    private Asset assets;
    private int stock;

//    private generateWarehouseStock()

}
