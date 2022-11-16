package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    Warehouse findWarehouseById(int warehouseId);
}
