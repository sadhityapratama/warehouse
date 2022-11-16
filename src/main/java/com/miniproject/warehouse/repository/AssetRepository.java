package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {

//    Asset findAssetByBarcode(String assetBarcode);
}
