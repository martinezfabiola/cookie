package com.example.cookie.persistance;

import com.example.cookie.models.ShopBag;
import org.springframework.data.repository.CrudRepository;

public interface ShopBagDao extends CrudRepository<ShopBag,Integer> {
}
