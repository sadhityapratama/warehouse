package com.miniproject.warehouse.controller;

import com.fasterxml.uuid.Generators;
import com.miniproject.warehouse.dto.AssetReport;
import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.model.Warehouse;
import com.miniproject.warehouse.repository.AssetRepository;
import com.miniproject.warehouse.response.HttpResponse;
import com.miniproject.warehouse.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import com.miniproject.warehouse.repository.StockRepository;
import com.miniproject.warehouse.repository.TransactionRepository;
import com.miniproject.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("asset")
@CrossOrigin(origins = "http://localhost:4100")
@Slf4j
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private AssetService assetService;

    @GetMapping("/listall")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponse getAllAsset(){
        HttpResponse httpResponse = new HttpResponse();
        List<Asset> assetList = assetRepository.findAll();
        httpResponse.setObject(assetList);

        if (assetList.isEmpty()){
            httpResponse.setMessage(HttpStatus.NO_CONTENT.name());
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
        }else {
            httpResponse.setMessage(HttpStatus.OK.name());
            httpResponse.setStatus(HttpStatus.OK.value());
        }
        return httpResponse;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponse getAssetByBarcode(@RequestParam("barcode") String barcode) throws Exception {
        log.info(barcode);
        Asset asset = assetService.getAsset(barcode);

        // Set Http Response
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setObject(asset);
        httpResponse.setMessage(HttpStatus.OK.name());
        httpResponse.setStatus(HttpStatus.OK.value());
        return httpResponse;
    }

    @GetMapping("/report")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponse getAssetReport() throws Exception {
        HttpResponse httpResponse = new HttpResponse();

        List<AssetReport> assetReportList = assetService.getAssetReport();
        httpResponse.setObject(assetReportList);
        if (assetReportList.isEmpty()){
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
    public HttpResponse addAsset(@RequestBody Asset asset) throws Exception {
        Asset assetResult = assetService.addAsset(asset);
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setObject(assetResult);
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setMessage(HttpStatus.OK.name());

        return httpResponse;
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponse updateAsset(@RequestBody Asset asset) throws Exception {

        Asset assetResult = assetService.updateAsset(asset);
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setObject(assetResult);
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setMessage(HttpStatus.OK.name());

        return httpResponse;
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponse deleteAsset(@RequestParam("barcode") String barcode) throws Exception {
        assetService.deleteAsset(barcode);
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setMessage(HttpStatus.OK.name());

        return httpResponse;
    }

}
