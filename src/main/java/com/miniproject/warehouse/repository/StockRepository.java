package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    List<Stock> findByWarehouseId(int warehouseId);
    Stock findStockByAssetBarcode(String assetBarcode);
}
