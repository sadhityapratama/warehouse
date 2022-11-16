package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;
=======
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
>>>>>>> e2a5c33543356144dbe10009f76bb2fbb5e61ce5

public interface AssetRepository extends JpaRepository<Asset, String> {

    Asset findAssetByBarcode(String assetBarcode);
}
