package com.miniproject.warehouse.service;

import com.miniproject.warehouse.dto.AssetStockbyWarehouse;
import com.miniproject.warehouse.model.Asset;
import com.miniproject.warehouse.model.Stock;
import com.miniproject.warehouse.model.Warehouse;
import com.miniproject.warehouse.repository.AssetRepository;
import com.miniproject.warehouse.repository.StockRepository;
import com.miniproject.warehouse.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class WarehouseService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ValidationService validationService;

//    public Asset insertAsset(Asset asset) throws Exception{
//        log.info("")
//    }

    public List<AssetStockbyWarehouse> getAllAssetStock(int warehouseId) throws Exception{

        log.info("[INQUIRY] Get Asset from Warehouse id {}", warehouseId);
        /**
         * Validation Step
         */
        validationService.validateIfWarehouseExists(warehouseId);

        Warehouse warehouse = warehouseRepository.findWarehouseById(warehouseId);

        List<Stock> stocks = stockRepository.findByWarehouseId(warehouseId);
        List<Asset> assets = assetRepository.findAssetByWarehouseId(warehouseId);

        HashMap<String,Stock> mapStockbyAssetBarcode = new HashMap<>();
        for(Stock stock: stocks){
            mapStockbyAssetBarcode.put(stock.getAssetBarcode(),stock);
        }

        List<AssetStockbyWarehouse> assetStockbyWarehouses = new ArrayList<>();
        for (Asset asset: assets) {
            AssetStockbyWarehouse assetStockbyWarehouse = new AssetStockbyWarehouse();
            assetStockbyWarehouse.setWarehouse(warehouse);
            assetStockbyWarehouse.setAssets(asset);
            assetStockbyWarehouse.setStock(mapStockbyAssetBarcode.get(asset.getBarcode()).getStock());
            assetStockbyWarehouses.add(assetStockbyWarehouse);
        }

        return assetStockbyWarehouses;
    }
}
