package com.example.cookie.persistance;

import com.example.cookie.models.PurchaseReceipt;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseReceiptDao extends CrudRepository<PurchaseReceipt, Integer> {
}
