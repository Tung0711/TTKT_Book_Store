package conn.ra.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class testController {
    @GetMapping("/index")
    public String Index() {
        return "home/index";
    }

    @GetMapping("/about-us")
    public String AboutUs() {
        return "home/about-us";
    }

    @GetMapping("/books-detail")
    public String BookDetail() {
        return "home/books-detail";
    }

    @GetMapping("/books-grid-view")
    public String BookGrid() {
        return "home/books-grid-view";
    }

    @GetMapping("/books-list")
    public String BookList() {
        return "home/books-list";
    }

    @GetMapping("/contact-us")
    public String ContactUs() {
        return "home/contact-us";
    }

    @GetMapping("/my-profile")
    public String MyProfile() {
        return "home/my-profile";
    }

    @GetMapping("/shop-cart")
    public String ShopCart() {
        return "home/shop-cart";
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
