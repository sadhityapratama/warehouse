package com.miniproject.warehouse.controller;

import com.fasterxml.uuid.Generators;
import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.repository.AssetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("asset")
@Slf4j
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;

    @GetMapping("/listall")
    @ResponseStatus(HttpStatus.OK)
    List<Asset> getAllAsset(){
        return assetRepository.findAll();
    }

    @GetMapping("/get/{barcode}")
    @ResponseStatus(HttpStatus.OK)
    Asset getAssetByBarcode(@PathVariable("barcode") String barcode){
        log.info(barcode);
        return assetRepository.findAssetByBarcode(barcode);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    Asset addAsset(@RequestBody Asset asset){
        UUID barcode = Generators.timeBasedGenerator().generate();
        asset.setBarcode(barcode.toString());

        return assetRepository.save(asset);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    Asset updateAsset(@RequestBody Asset asset){
        return assetRepository.save(asset);
    }

    @DeleteMapping("/delete/{barcode}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAsset(@PathVariable("barcode") String barcode){

        assetRepository.deleteById(barcode);

        return "DELETED SUCCESSFULLY";
    }



}
