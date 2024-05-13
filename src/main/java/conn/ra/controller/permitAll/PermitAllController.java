package conn.ra.controller.permitAll;

import conn.ra.model.entity.Book;
import conn.ra.model.entity.Categories;
import conn.ra.model.entity.User;
import conn.ra.security.UserDetail.UserLoggedIn;
import conn.ra.service.BookService;
import conn.ra.service.CategoriesService;
import conn.ra.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class PermitAllController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private UserLoggedIn userLoggedIn;

    @GetMapping("/sign-in")
    public String SignIn() {
        return "home/shop-login";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        User user = new User ();
        model.addAttribute ( "user", user );
        return "home/shop-registration";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute("user") User user) {
        userService.register ( user );
        return "redirect:/sign-in";
    }

    @GetMapping("/index")
    public String Index(HttpSession session, Model model,
                        @RequestParam(defaultValue = "8", name = "limit") int limit,
                        @RequestParam(defaultValue = "2", name = "page") int page,
                        @RequestParam(defaultValue = "id", name = "sort") String sort,
                        @RequestParam(defaultValue = "asc", name = "order") String order
    ) {
        Pageable pageable;
        if (order.equals ( "asc" )) {
            pageable = PageRequest.of ( page, limit, Sort.by ( sort ).ascending () );
        } else {
            pageable = PageRequest.of ( page, limit, Sort.by ( sort ).descending () );
        }
        Page<Book> books = bookService.getByCategoryStatus ( pageable, true );
        model.addAttribute ( "books", books );
        List<Categories> categories = categoriesService.getByStatus ();
        session.setAttribute ( "categories", categories );
        return "home/index";
    }

    @GetMapping("/about-us")
    public String AboutUs() {
        return "home/about-us";
    }

    @GetMapping("/contact-us")
    public String ContactUs() {
        return "home/contact-us";
    }
}
