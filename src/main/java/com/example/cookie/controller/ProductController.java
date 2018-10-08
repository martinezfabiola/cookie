package com.example.cookie.controller;

import com.example.cookie.models.Product;
import com.example.cookie.models.PurchaseReceipt;
import com.example.cookie.models.ShopBag;
import com.example.cookie.models.User;
import com.example.cookie.persistance.ProductDao;
import com.example.cookie.persistance.PurchaseReceiptDao;
import com.example.cookie.persistance.ShopBagDao;
import com.example.cookie.persistance.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yannica Djaffar on 08/10/2018
 * @author Marie-Odile McKeeney on 08/10/2018
 * @author Fabiola Martinez on 08/10/2018
 */

@Controller
public class ProductController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PurchaseReceiptDao purchasereceiptDao;

    @Autowired
    private ShopBagDao shopbagDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    /*----------------------------
               MAPPING
    -----------------------------*/

    @GetMapping("/atelier")
    public String getAtelier(Model model) {
        model.addAttribute("productAForm", new PurchaseReceipt());
        return "atelier";
    }

    @GetMapping("/commande")
    public String showCommande(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int current_wallet = 0;

        for (User u : userDao.findAll()) {
            if (user.getEmail().equals(u.getEmail())) {
                for (ShopBag shopbag : shopbagDao.findAll()) {
                    if (u.getShopbag().getId() == shopbag.getId()) {
                        current_wallet = u.getWallet() - shopbag.getTotal();
                    }
                }
            }
        }

        model.addAttribute("current_wallet", current_wallet);
        return "commande";
    }

    @GetMapping("/panier")
    public String showPannier() {
        return "cookie_panier";
    }

    /*----------------------------
        MAPPING FOR ADD COOKIE
    -----------------------------*/

    @GetMapping("/produit")
    public String getProduit(Model model) {
        model.addAttribute("productForm", new PurchaseReceipt());
        return "cookie_produit";
    }

    @GetMapping("/produitdc")
    public String getProduitdc(Model model) {
        model.addAttribute("productFormDC", new PurchaseReceipt());
        return "cookie_dc";
    }

    @GetMapping("/produitcn")
    public String getProduitcn(Model model) {
        model.addAttribute("productFormCN", new PurchaseReceipt());
        return "cookie_cn";
    }

    @GetMapping("/produitmandm")
    public String getProduitmm(Model model) {
        model.addAttribute("productFormMM", new PurchaseReceipt());
        return "cookie_mm";
    }

    @GetMapping("/produitm")
    public String getProduitm(Model model) {
        model.addAttribute("productFormM", new PurchaseReceipt());
        return "cookie_m";
    }

    @GetMapping("/produitn")
    public String getProduitn(Model model) {
        model.addAttribute("productFormN", new PurchaseReceipt());
        return "cookie_n";
    }


    @Scope("session")
    @PostMapping("/product")
    public String addProduct(HttpServletRequest request, ModelMap model, PurchaseReceipt purchasereceipt, HttpSession session) {

        ShopBag shopbag = checkShopbag(request);
        int total = shopbag.getTotal();

        File photo = new File("../img/cookiechocb.png");

        Product cb = new Product("chocolat blanc", 2, photo);

        int current_quantity = checkQuantity(cb, purchasereceipt, checkShopbag(request));
        int curren_total = (cb.getPrice() * purchasereceipt.getQuantity()) + total;

        purchasereceipt.setShopbag(shopbag);
        purchasereceipt.setProduct(cb);
        purchasereceipt.setQuantity(current_quantity);
        purchasereceiptDao.save(purchasereceipt);

        shopbag.setTotal(curren_total);
        shopbagDao.save(shopbag);

        model.addAttribute("items", showItem(request));
        model.addAttribute("total", curren_total);

        return "cookie_panier";
    }

    @Scope("session")
    @PostMapping("/productDC")
    public String addProductDC(HttpServletRequest request, ModelMap model, PurchaseReceipt purchasereceipt, HttpSession session) {

        ShopBag shopbag = checkShopbag(request);
        int total = shopbag.getTotal();

        File photo = new File("../img/cookiedoublechoc.png");

        Product cb = new Product("Double Chocolat", 2, photo);

        int current_quantity = checkQuantity(cb, purchasereceipt, checkShopbag(request));
        int curren_total = (cb.getPrice() * purchasereceipt.getQuantity()) + total;

        purchasereceipt.setShopbag(shopbag);
        purchasereceipt.setProduct(cb);
        purchasereceipt.setQuantity(current_quantity);
        purchasereceiptDao.save(purchasereceipt);

        shopbag.setTotal(curren_total);
        shopbagDao.save(shopbag);

        model.addAttribute("items", showItem(request));
        model.addAttribute("total", curren_total);

        return "cookie_panier";
    }

    @Scope("session")
    @PostMapping("/productCN")
    public String addProductCN(HttpServletRequest request, ModelMap model, PurchaseReceipt purchasereceipt, HttpSession session) {

        ShopBag shopbag = checkShopbag(request);
        int total = shopbag.getTotal();

        File photo = new File("../img/cookiechocn.png");

        Product cb = new Product("Chocolat Noir", 2, photo);

        int current_quantity = checkQuantity(cb, purchasereceipt, checkShopbag(request));
        int curren_total = (cb.getPrice() * purchasereceipt.getQuantity()) + total;

        purchasereceipt.setShopbag(shopbag);
        purchasereceipt.setProduct(cb);
        purchasereceipt.setQuantity(current_quantity);
        purchasereceiptDao.save(purchasereceipt);

        shopbag.setTotal(curren_total);
        shopbagDao.save(shopbag);

        model.addAttribute("items", showItem(request));
        model.addAttribute("total", curren_total);

        return "cookie_panier";
    }

    @Scope("session")
    @PostMapping("/productMandM")
    public String addProductMM(HttpServletRequest request, ModelMap model, PurchaseReceipt purchasereceipt, HttpSession session) {

        ShopBag shopbag = checkShopbag(request);
        int total = shopbag.getTotal();

        File photo = new File("../img/cookiemandm.png");

        Product cb = new Product("M&M", 2, photo);

        int current_quantity = checkQuantity(cb, purchasereceipt, checkShopbag(request));
        int curren_total = (cb.getPrice() * purchasereceipt.getQuantity()) + total;

        purchasereceipt.setShopbag(shopbag);
        purchasereceipt.setProduct(cb);
        purchasereceipt.setQuantity(current_quantity);
        purchasereceiptDao.save(purchasereceipt);

        shopbag.setTotal(curren_total);
        shopbagDao.save(shopbag);

        model.addAttribute("items", showItem(request));
        model.addAttribute("total", curren_total);

        return "cookie_panier";
    }

    @Scope("session")
    @PostMapping("/productM")
    public String addProductM(HttpServletRequest request, ModelMap model, PurchaseReceipt purchasereceipt, HttpSession session) {

        if (session == null || session.getAttribute("user") == null) {
            // user is not logged in, redirect to home page
            return "redirect:/connexion";
        } else {
            ShopBag shopbag = checkShopbag(request);
            int total = shopbag.getTotal();

            File photo = new File("../img/cookiemyrt.png");

            Product cb = new Product("Myrtilles", 2, photo);

            int current_quantity = checkQuantity(cb, purchasereceipt, checkShopbag(request));
            int curren_total = (cb.getPrice() * purchasereceipt.getQuantity()) + total;

            purchasereceipt.setShopbag(shopbag);
            purchasereceipt.setProduct(cb);
            purchasereceipt.setQuantity(current_quantity);
            purchasereceiptDao.save(purchasereceipt);

            shopbag.setTotal(curren_total);
            shopbagDao.save(shopbag);

            model.addAttribute("items", showItem(request));
            model.addAttribute("total", curren_total);

            return "cookie_panier";
        }
    }

    @Scope("session")
    @PostMapping("/productN")
    public String addProductN(HttpServletRequest request, ModelMap model, PurchaseReceipt purchasereceipt, HttpSession session) {

        ShopBag shopbag = checkShopbag(request);
        int total = shopbag.getTotal();

        File photo = new File("../img/cookienature.png");

        Product cb = new Product("Nature", 2, photo);

        int current_quantity = checkQuantity(cb, purchasereceipt, checkShopbag(request));
        int curren_total = (cb.getPrice() * purchasereceipt.getQuantity()) + total;

        purchasereceipt.setShopbag(shopbag);
        purchasereceipt.setProduct(cb);
        purchasereceipt.setQuantity(current_quantity);
        purchasereceiptDao.save(purchasereceipt);

        shopbag.setTotal(curren_total);
        shopbagDao.save(shopbag);

        model.addAttribute("items", showItem(request));
        model.addAttribute("total", (curren_total));

        return "cookie_panier";
    }

    @Scope("session")
    @PostMapping("/productAtelier")
    public String addProductAtelier(HttpServletRequest request, ModelMap model, PurchaseReceipt purchasereceipt, HttpSession session) {

        ShopBag shopbag = checkShopbag(request);
        int total = shopbag.getTotal();

        File photo = new File("../img/cookie-x.png");

        Product cb = new Product("Atelier", 5, photo);

        int current_quantity = checkQuantity(cb, purchasereceipt, checkShopbag(request));
        int curren_total = (cb.getPrice() * purchasereceipt.getQuantity()) + total;

        purchasereceipt.setShopbag(shopbag);
        purchasereceipt.setProduct(cb);
        purchasereceipt.setQuantity(current_quantity);
        purchasereceiptDao.save(purchasereceipt);

        shopbag.setTotal(curren_total);
        shopbagDao.save(shopbag);

        model.addAttribute("items", showItem(request));
        model.addAttribute("total", (curren_total));

        return "cookie_panier";
    }

    @Scope("session")
    @GetMapping("/deleteShopbag")
    public String deleteShopbag(HttpServletRequest request, ModelMap model) {

        List<Item> items = new ArrayList<Item>();

        ShopBag shopbag = checkShopbag(request);
        shopbag.setTotal(0);

        for (PurchaseReceipt purchase : purchasereceiptDao.findAll()) {
            if (purchase.getShopbag() == shopbag) {
                purchasereceiptDao.delete(purchase);
            }
        }

        model.addAttribute("items", items);
        model.addAttribute("total", 0);

        return "cookie_panier";
    }

    /*----------------------------
           OTHER METHODS
    -----------------------------*/
    // Class for items inside the shopping car
    public class Item {

        private File photo;

        private int price;

        private int quantity;

        public Item(File photo, int price, int quantity) {
            this.photo = photo;
            this.price = price;
            this.quantity = quantity;
        }

        public File getPhoto() {
            return photo;
        }

        public void setPhoto(File photo) {
            this.photo = photo;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    // Returns the shopbag object of an specific user
    public ShopBag checkShopbag(HttpServletRequest request) {
        ShopBag shopbag = new ShopBag();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        // user IS logged in, do something: set model or do whatever you need
        for (User u : userDao.findAll()) {
            if (user.getEmail().equals(u.getEmail())) {
                shopbag = u.getShopbag();
            }
        }
        return shopbag;
    }

    // Returns list of items bought
    public List<Item> showItem(HttpServletRequest request) {

        List<Item> items = new ArrayList<>();

        ShopBag shopbag = checkShopbag(request);

        for (PurchaseReceipt purchase : purchasereceiptDao.findAll()) {

            if (shopbag.getId() == purchase.getShopbag().getId()) {

                Product product = purchase.getProduct();

                Item item = new Item(product.getPhoto(), product.getPrice(), purchase.getQuantity());
                items.add(item);
            }
        }

        return items;
    }

    // Returns the current product's quantity
    public int checkQuantity(Product product, PurchaseReceipt currentPurchase, ShopBag userShopbag) {

        int current_quantity = currentPurchase.getQuantity();

        for (PurchaseReceipt purchase : purchasereceiptDao.findAll()) {
            if (purchase.getShopbag() == userShopbag) {
                if (purchase.getProduct().getName().equals(product.getName())) {

                    int quantity = purchase.getQuantity();
                    purchasereceiptDao.delete(purchase);
                    current_quantity = quantity + currentPurchase.getQuantity();
                }
            }
        }
        return current_quantity;
    }

    // Get wallet amount
    public int checkWallet(User user, ShopBag shopBag) {

        int current_wallet = user.getWallet() - shopBag.getTotal();

        return current_wallet;
    }

}
