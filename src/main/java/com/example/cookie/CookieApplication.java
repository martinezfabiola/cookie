package com.example.cookie;

import com.example.cookie.models.User;
import com.example.cookie.persistance.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories
public class CookieApplication {

	 @Autowired
	 private UserDao userdao;

	public static void main(String[] args) {
		SpringApplication.run(CookieApplication.class, args);
	}

/*@PostConstruct
public void init(){
	userdao.save( new User(null, "Marie", "Gatinois","moi@gmail.com","0647668742","16 rue de la ferme", "95230 Soisy", "passwordturfu", null, null ));
}*/
}
