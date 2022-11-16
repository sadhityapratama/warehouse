package com.miniproject.warehouse.controller;

import com.miniproject.warehouse.dto.AssetStockbyWarehouse;
import com.miniproject.warehouse.model.Warehouse;
import com.miniproject.warehouse.repository.WarehouseRepository;
import com.miniproject.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("warehouse")
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
    public List<AssetStockbyWarehouse> getAllStockbyWarehouse(@RequestParam("id") int id){
        return warehouseService.getAllAssetStock(id);
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    Warehouse getWarehouseById(@RequestParam("id") int id){
        return warehouseRepository.findWarehouseById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Warehouse updateWarehouse(@RequestBody Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteWarehouse(@RequestParam("id") int id){

        warehouseRepository.deleteById(id);

        return "DELETED SUCCESFULLY";
    }

}
