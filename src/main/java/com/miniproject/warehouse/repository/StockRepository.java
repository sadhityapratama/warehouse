package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    List<Stock> findByWarehouseId(int warehouseId);
    List<Stock> findStockByAssetBarcode(String assetBarcode);

    @Query(value = "SELECT * FROM `stock` WHERE asset_barcode = :assetBarcode and warehouse_id = :warehouseId", nativeQuery = true)
    Stock findStockByAssetBarcodeAndWarehouseId(String assetBarcode,int warehouseId);

}
