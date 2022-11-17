package com.miniproject.warehouse.repository;


import com.miniproject.warehouse.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findTransactionById(int transactionId);
    @Query(value = "SELECT * FROM `transaction` WHERE asset_barcode = :assetBarcode and transaction_type = :transactionType ORDER BY transaction_date desc", nativeQuery = true)
    List<Transaction> findTransactionByAssetBarcode(String assetBarcode, String transactionType);

    @Query(value = "SELECT * FROM `transaction` WHERE warehouse_id = :warehouseId and transaction_type = :transactionType ORDER BY transaction_date desc", nativeQuery = true)
    List<Transaction> findTransactionByWarehouseId(int warehouseId, String transactionType);

}
