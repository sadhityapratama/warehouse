package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AssetRepository extends JpaRepository<Asset, String> {

    Asset findAssetByBarcode(String assetBarcode);
}
