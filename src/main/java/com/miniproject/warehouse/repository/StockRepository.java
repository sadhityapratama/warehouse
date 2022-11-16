package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Stock findStockByAssetBarcode(String assetBarcode);
}
