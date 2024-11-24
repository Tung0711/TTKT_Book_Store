package conn.ra.controller.users;

import conn.ra.model.dto.request.ShoppingCartRequest;
import conn.ra.model.entity.Book;
import conn.ra.model.entity.ShoppingCart;
import conn.ra.model.entity.User;
import conn.ra.security.UserDetail.UserLoggedIn;
import conn.ra.service.BookService;
import conn.ra.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
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
            totalPrice += item.getBooks ().getPrice () * item.getOrderQuantity ();
        }
        model.addAttribute ( "totalPrice", totalPrice );
        return "home/shop-cart";
    }

    @GetMapping("/shop-cart/{id}")
    public String createCart(@PathVariable("id") Long id) {
        User user = userLoggedIn.getUserLoggedIn ();
        Book books = bookService.findById ( id );
        ShoppingCart shoppingCart = shoppingCartService.findByUsersAndBook ( user, books );
        if (shoppingCart == null) {
            ShoppingCartRequest shoppingCartRequest = new ShoppingCartRequest ();
            shoppingCartRequest.setBookId ( id );
            shoppingCartRequest.setOrderQuantity ( 1 );
            shoppingCartService.add ( shoppingCartRequest, user );
        } else {
            shoppingCart.setOrderQuantity ( shoppingCart.getOrderQuantity () + 1 );
            shoppingCartService.save ( shoppingCart );
        }
        return "redirect:/user/shop-cart";
    }

    @PostMapping("/add-cart/{id}")
    public String addCart(@PathVariable Long id, @RequestParam("quantity") int quantity) {
        User user = userLoggedIn.getUserLoggedIn ();
        Book books = bookService.findById ( id );
        ShoppingCart shoppingCart = shoppingCartService.findByUsersAndBook ( user, books );
        if (shoppingCart == null) {
            ShoppingCart cart = new ShoppingCart ();
            cart.setBooks ( bookService.findById ( id ) );
            cart.setOrderQuantity ( quantity );
            cart.setUsers ( user );
            shoppingCartService.save ( cart );
        } else {
            shoppingCart.setOrderQuantity ( quantity + shoppingCart.getOrderQuantity () );
            shoppingCartService.save ( shoppingCart );
        }
        return "redirect:/user/shop-cart";
    }

    @GetMapping("/delete-cart/{id}")
    public String deleteCart(@PathVariable("id") Long id) {
        User user = userLoggedIn.getUserLoggedIn ();
        Book books = bookService.findById ( id );
        ShoppingCart shoppingCart = shoppingCartService.findByUsersAndBook ( user, books );
        if (shoppingCart != null) {
            shoppingCartService.delete ( shoppingCart.getId () );
        }
        return "redirect:/user/shop-cart";
    }

    @PostMapping("/update-quantity/{id}")
    @ResponseBody
    public ShoppingCart updateQuantity(@PathVariable("id") Long id, @RequestParam("quantity") int quantity) {
        User user = userLoggedIn.getUserLoggedIn ();
        Book books = bookService.findById ( id );
        ShoppingCart shoppingCart = shoppingCartService.findByUsersAndBook ( user, books );
        shoppingCart.setOrderQuantity ( quantity );
        return shoppingCartService.save ( shoppingCart );
    }
}
