package com.miniproject.warehouse.repository;

import com.miniproject.warehouse.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
