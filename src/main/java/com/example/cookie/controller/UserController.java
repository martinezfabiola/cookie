package com.example.cookie.controller;

import com.example.cookie.models.ConnexionInfo;
import com.example.cookie.models.User;
import com.example.cookie.persistance.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

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

    /*----------------------------
               MAPPING
    -----------------------------*/

    @GetMapping("/inscription")
    public String getInscription(Model model) {
        model.addAttribute("userForm", new User());
        return "cookie_inscription";
    }

    @PostMapping("/inscription")
    public String addUser(User user, Model model) {
        if (checkInscription(user) == true) {
            userDao.save(user);
            return "redirect:/accueil";
        }
        return "redirect:/inscription"; //TODO add message "compte déjà existant"
    }

    @GetMapping("/connexion")
    public String getConnexion(Model model) {
        model.addAttribute("userForm", new ConnexionInfo());
        return "cookie_connexion";
    }

    @PostMapping("/connexion")
    public String userConnexion(User user, Model model) {
        if (checkConnexion(user) == true) {
            return "redirect:/accueil";
        } else {
            return "redirect:/oops";
        }
    }


    @GetMapping("/accueil")
    public String getAccueil(Model model) {
        return "index";
    }

    @GetMapping("/oops")
    public String getOops(Model model) {
        return "oops";
    }

    @GetMapping("/produit")
    public String getProduit(Model model) {
        return "cookie_produit";
    }

    @GetMapping("/panier")
    public String getPanier(Model model) {
        return "cookie_panier";
    }

    /*----------------------------
           OTHER METHODS
    -----------------------------*/

    //Check in DB if input email and input password match
    public boolean checkConnexion(User user) {
        for (User u : userDao.findAll()) {
            if (user.getEmail().equals(u.getEmail())) {
                if (user.getPassword().equals(u.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    //Check in DB if email doesn't exist
    public boolean checkInscription(User user) {
        for (User u : userDao.findAll()) {
            if (user.getEmail().equals(u.getEmail())) {
                return false;
            }
        }
        return true;
    }

}

