package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

//    Asset findAssetByBarcode(String assetBarcode);
}
