package com.example.cookie.controller;

import com.example.cookie.models.ConnexionInfo;
import com.example.cookie.models.ShopBag;
import com.example.cookie.models.User;
import com.example.cookie.persistance.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Yannica Djaffar on 08/10/2018
 * @author Marie-Odile McKeeney on 08/10/2018
 * @author Fabiola Martinez on 08/10/2018
 */
@Controller
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

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
            ShopBag shopbag = new ShopBag(0);
            user.setShopbag(shopbag);
            user.setWallet(100);
            userDao.save(user);
            return "redirect:/connexion";
        }
        return "redirect:/inscription";
    }

    @GetMapping("/connexion")
    public String getConnexion(HttpServletRequest request, Model model) {
        model.addAttribute("userForm", new ConnexionInfo());
        clearSession(request, request.getSession());
        return "cookie_connexion";
    }

    @PostMapping("/connexion")
    public String userConnexion(HttpServletRequest request, User user, Model model) {
        if (checkConnexion(user) == true) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("nextProd", 1);
            return "redirect:/accueil";
        } else {
            return "redirect:/oops";
        }
    }

    @PostMapping("/panier")
    public String buyOnlyIfConnected(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // user is not logged in, do something about it
            return "redirect:/accueil";
        } else {
            // user IS logged in, do something: set model or do whatever you need
            return "redirect:/panier";
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

    public void clearSession(HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        session.removeAttribute("user");
    }

}

