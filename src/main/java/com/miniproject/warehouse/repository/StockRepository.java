package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
