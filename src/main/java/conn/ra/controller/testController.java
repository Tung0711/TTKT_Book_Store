package conn.ra.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class testController {
//    @GetMapping("/books-detail")
//    public String BookDetail() {
//        return "home/books-detail";
//    }
    @GetMapping("/books-list")
    public String BookList() {
        return "home/books-list";
    }

    @GetMapping("/my-profile")
    public String MyProfile() {
        return "home/my-profile";
    }

    @GetMapping("/shop-checkout")
    public String ShopCheckout() {
        return "home/shop-checkout";
    }
    @GetMapping("/wishlist")
    public String Wishlist() {
        return "home/wishlist";
    }
}
