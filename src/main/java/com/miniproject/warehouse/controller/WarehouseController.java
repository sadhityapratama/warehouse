package com.miniproject.warehouse.controller;

import com.miniproject.warehouse.dto.AssetStockbyWarehouse;
import com.miniproject.warehouse.model.Warehouse;
import com.miniproject.warehouse.repository.WarehouseRepository;
import com.miniproject.warehouse.response.HttpResponse;
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
    public HttpResponse getAllWarehouse(){
        HttpResponse httpResponse = new HttpResponse();
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        httpResponse.setObject(warehouseList);
        if (warehouseList.isEmpty()){
            httpResponse.setMessage(HttpStatus.NO_CONTENT.name());
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
        }else {
            httpResponse.setMessage(HttpStatus.OK.name());
            httpResponse.setStatus(HttpStatus.OK.value());
        }

        return httpResponse;
    }

    @GetMapping("/liststock")
    public HttpResponse getAllStockbyWarehouse(@RequestParam("id") int warehouseId) throws Exception{
        log.info("[INQUIRY] Get Asset from Warehouse id {} Sent", warehouseId);
        HttpResponse httpResponse = new HttpResponse();
        List<AssetStockbyWarehouse> assetStockbyWarehouseList = warehouseService.getAllAssetStock(warehouseId);
        httpResponse.setObject(assetStockbyWarehouseList);

        if (assetStockbyWarehouseList.isEmpty()){
            httpResponse.setMessage(HttpStatus.NO_CONTENT.name());
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
        }else {
            httpResponse.setMessage(HttpStatus.OK.name());
            httpResponse.setStatus(HttpStatus.OK.value());
        }
        return httpResponse;
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponse getWarehouseById(@PathVariable("id") int warehouseId){
        HttpResponse httpResponse = new HttpResponse();
        Warehouse warehouse =  warehouseRepository.findWarehouseById(warehouseId);
        httpResponse.setObject(warehouse);
        if (warehouse == null){
            httpResponse.setMessage(HttpStatus.NO_CONTENT.name());
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
        }else {
            httpResponse.setMessage(HttpStatus.OK.name());
            httpResponse.setStatus(HttpStatus.OK.value());
        }
        return httpResponse;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponse addWarehouse(@RequestBody Warehouse warehouse){
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setObject(warehouseRepository.save(warehouse));
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setMessage(HttpStatus.OK.name());
        return httpResponse;
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponse updateWarehouse(@RequestBody Warehouse warehouse){
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setObject(warehouseRepository.save(warehouse));
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setMessage(HttpStatus.OK.name());
        return httpResponse;
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    HttpResponse deleteWarehouse(@RequestParam("id") int warehouseId){
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setMessage("SUCCESSFULLY DELETED!");
        warehouseRepository.deleteById(warehouseId);

        return httpResponse;
    }

}
