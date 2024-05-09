package conn.ra.controller.users;

import conn.ra.model.entity.Book;
import conn.ra.model.entity.ShoppingCart;
import conn.ra.model.entity.User;
import conn.ra.security.UserDetail.UserLoggedIn;
import conn.ra.security.UserDetail.UserPrincipal;
import conn.ra.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class CartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserLoggedIn userLoggedIn;

    @GetMapping("/shop-cart")
    public String shopCart(Model model) {
        User user = userLoggedIn.getUserLoggedIn ();
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAllByUser ( user );
        model.addAttribute ( "shoppingCarts", shoppingCarts );
        double totalPrice = (double) 0;
        for (ShoppingCart item : shoppingCarts) {
            totalPrice += item.getBook ().getPrice () * item.getOrderQuantity ();
        }
        model.addAttribute ( "totalPrice", totalPrice );
        return "home/shop-cart";
    }

    @PostMapping("/add-shop-cart/{id}")
    public String addCart(@PathVariable Long id) {
        User user = userLoggedIn.getUserLoggedIn ();
        Book book = bookService.findById ( id );
        ShoppingCart shoppingCart = shoppingCartService.findByUsersAndBook ( user, book );
        if (shoppingCart != null) {
            shoppingCart.setOrderQuantity ( 1 + shoppingCart.getOrderQuantity () );
            shoppingCartService.save ( shoppingCart );
        } else {
            ShoppingCart cart = new ShoppingCart ();
            cart.setBook ( bookService.findById ( id ) );
            cart.setOrderQuantity ( 1 );
            cart.setUsers ( user );
            shoppingCartService.save ( cart );
        }
        return "redirect:/user/shop-cart";
    }

    @PostMapping("/edit-shop-cart/{id}")
    public String editCart(
            @PathVariable("id") Long id,
            @RequestParam("quantity") int quantity) {
        User user = userLoggedIn.getUserLoggedIn ();
        shoppingCartService.updateQuantity ( id, quantity );
        return "redirect:/user/shop-cart";
    }

    @GetMapping("/delete-cart/{id}")
    public String deleteCart(@PathVariable("id") Long id) {
        User user = userLoggedIn.getUserLoggedIn ();
        Book book = bookService.findById ( id );
        ShoppingCart shoppingCart = shoppingCartService.findByUsersAndBook ( user, book );
        if (shoppingCart != null) {
            shoppingCartService.delete ( shoppingCart.getId () );
        }
        return "redirect:/user/shop-cart";
    }

    @PostMapping("/update-quantity/{id}")
    @ResponseBody
    public ShoppingCart updateQuantity(@PathVariable("id") Long id, @RequestParam("quantity") int quantity) {
        User user = userLoggedIn.getUserLoggedIn ();
        Book book = bookService.findById ( id );
        ShoppingCart shoppingCart = shoppingCartService.findByUsersAndBook ( user, book );
        shoppingCart.setOrderQuantity ( quantity );
        return shoppingCartService.save ( shoppingCart );
    }
}
