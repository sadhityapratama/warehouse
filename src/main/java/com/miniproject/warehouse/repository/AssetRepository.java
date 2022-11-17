package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {

    @Query(value = "SELECT asset.* FROM `stock` JOIN `asset` ON `stock`.`asset_barcode` = `asset`.`barcode` WHERE warehouse_id = :warehouseId", nativeQuery = true)
    List<Asset> findAssetByWarehouseId(int warehouseId);
    Asset findAssetByBarcode(String assetBarcode);
    Boolean existsByBarcode(String assetBarcode);
}
