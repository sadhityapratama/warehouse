package com.miniproject.warehouse.controller;

import com.miniproject.warehouse.model.Asset;
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

    @GetMapping("/listall")
    @ResponseStatus(HttpStatus.OK)
    public List<Warehouse> getAllWarehhouse(){
        return warehouseRepository.findAll();
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    Warehouse getWarehouseById(@PathVariable("id")int id){
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
    public String deleteWarehouse(@PathVariable("id")int id){

        warehouseRepository.deleteById(id);

        return "DELETED SUCCESFULLY";
    }

}
