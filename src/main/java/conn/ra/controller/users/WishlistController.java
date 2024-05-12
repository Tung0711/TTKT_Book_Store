package conn.ra.controller.users;

import conn.ra.model.dto.request.WishlistRequest;
import conn.ra.model.entity.Book;
import conn.ra.model.entity.User;
import conn.ra.model.entity.WishList;
import conn.ra.security.UserDetail.UserLoggedIn;
import conn.ra.service.BookService;
import conn.ra.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private UserLoggedIn userLoggedIn;
    @Autowired
    private BookService bookService;

    @GetMapping("/wishlist/{id}")
    public String addWishList(@PathVariable("id") Long id) {
        User user = userLoggedIn.getUserLoggedIn ();
        Book book = bookService.findById ( id );
        WishList wishList = wishlistService.findByUserAndBook ( user, book );
        if (wishList == null) {
            WishlistRequest wishListRequest = new WishlistRequest ();
            wishListRequest.setBookId ( id );
            wishlistService.add ( user, wishListRequest );
        }
        return "redirect:/user/wishlist";
    }

    @GetMapping("/wishlist")
    public String getAll(Model model) {
        User user = userLoggedIn.getUserLoggedIn ();
        List<WishList> wishLists = wishlistService.getAll ( user );
        model.addAttribute ( "wishlist", wishLists );
        return "/home/wishlist";
    }

    @GetMapping("/delete-wishlist/{id}")
    public String delete(@PathVariable("id") Long wishlistId) {
        User user = userLoggedIn.getUserLoggedIn ();
        wishlistService.delete ( wishlistId, user );
        return "redirect:/user/wishlist";
    }
}
