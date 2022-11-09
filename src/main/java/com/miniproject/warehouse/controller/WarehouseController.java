package com.miniproject.warehouse.controller;

import com.miniproject.warehouse.model.Warehouse;
import com.miniproject.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @GetMapping("/list-of-warehouse")
    @ResponseStatus(HttpStatus.OK)
    public List<Warehouse> getAllWarehhouse(){
        return warehouseRepository.findAll();
    }

    @PostMapping("/add-warehouse")
    @ResponseStatus(HttpStatus.OK)
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

}
