package com.example.cookie.persistance;

import com.example.cookie.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {
}
