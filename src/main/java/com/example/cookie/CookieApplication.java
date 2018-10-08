package com.example.cookie;

import com.example.cookie.persistance.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CookieApplication {

	 @Autowired
	 private UserDao userdao;

	public static void main(String[] args) {
		SpringApplication.run(CookieApplication.class, args);
	}

}
