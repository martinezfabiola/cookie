package com.example.cookie.persistance;

import com.example.cookie.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product, Integer> {
}
