package com.miniproject.warehouse.controller;

import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("asset")
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;

    @GetMapping("/list-of-assets")
    @ResponseStatus(HttpStatus.OK)
    List<Asset> getAllAsset(){
        return assetRepository.findAll();
    }

    @PostMapping("/add-asset")
    @ResponseStatus(HttpStatus.OK)
    Asset addAsset(@RequestBody Asset asset){
        return assetRepository.save(asset);
    }

    @PutMapping("/update-asset")
    @ResponseStatus(HttpStatus.OK)
    Asset updateAsset(@RequestBody  Asset asset){
        return assetRepository.save(asset);
    }

    @DeleteMapping("/delete-asset")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAsset(){
//        assetRepository.delete();
    }



}
