package com.example.cookie.controller;

import com.example.cookie.models.User;
import com.example.cookie.persistance.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 10/09/2018
 */
@Controller
public class UserController {

    private final UserDao userDao;


    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Ceci sera mappé sur l'URL '/users'.
     * C'est le routeur de Spring MVC qui va détecter et appeler directement cette méthode.
     * Il lui fournira un "modèle", auquel on pourra rajouter des attributs.
     * Ce modèle sera ensuite forwardé à une page web (dans resources/templates).
     * Le nom de la template est retourné par la fonction. Ici, elle appelle donc le template "users".
     *
     * @param model le modèle
     * @return
     */
    @GetMapping("/inscription")
    public String getInscription(Model model) {
        model.addAttribute("userForm", new User());
        return "cookie_inscription";
    }

    @GetMapping("/connexion")
    public String getConnexion(Model model) {
        return "cookie_connexion";
    }

    @GetMapping("/accueil")
    public String getAccueil(Model model) {
        return "index";
    }

    @PostMapping("/inscription")
    public String addUser(User user, Model model){
        user.setHistorique(null);
        user.setShopbag(null);
        userDao.save(user);
        return "redirect:/accueil";
    }

}

