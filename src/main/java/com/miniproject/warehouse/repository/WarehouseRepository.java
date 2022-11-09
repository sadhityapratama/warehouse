package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    Warehouse findWarehouseById(int warehouseId);
}
