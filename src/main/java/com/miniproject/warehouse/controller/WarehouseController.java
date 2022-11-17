package com.miniproject.warehouse.controller;

import com.miniproject.warehouse.dto.AssetStockbyWarehouse;
import com.miniproject.warehouse.model.Warehouse;
import com.miniproject.warehouse.repository.WarehouseRepository;
import com.miniproject.warehouse.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("warehouse")
@Slf4j
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/listall")
    @ResponseStatus(HttpStatus.OK)
    public List<Warehouse> getAllWarehouse(){
        return warehouseRepository.findAll();
    }

    @GetMapping("/liststock")
    public List<AssetStockbyWarehouse> getAllStockbyWarehouse(@RequestParam("id") int warehouseId) throws Exception{
        log.info("[INQUIRY] Get Asset from Warehouse id {} Sent", warehouseId);
        return warehouseService.getAllAssetStock(warehouseId);
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    Warehouse getWarehouseById(@RequestParam("id") int warehouseId) throws Exception{
        return warehouseRepository.findWarehouseById(warehouseId);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse) throws Exception{
        return warehouseService.insertWarehouse(warehouse);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Warehouse updateWarehouse(@RequestBody Warehouse warehouse) throws Exception{
        return warehouseService.updateWarehouse(warehouse);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public Warehouse deleteWarehouse(@RequestParam("id") int warehouseId) throws Exception{

        return warehouseService.deleteWarehouse(warehouseId);
    }

}
