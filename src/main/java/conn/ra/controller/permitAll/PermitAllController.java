package conn.ra.controller.permitAll;

import conn.ra.model.dto.request.PasswordRequest;
import conn.ra.model.dto.request.UserRegister;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class PermitAllController {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private BookService bookService;


    @GetMapping("/index")
    public String Index(HttpSession session, Model model,
                        @RequestParam(name = "keyword", required = false) String keyword,
                        @RequestParam(defaultValue = "12", name = "limit") int limit,
                        @RequestParam(defaultValue = "0", name = "page") int page,
                        @RequestParam(defaultValue = "id", name = "sort") String sort,
                        @RequestParam(defaultValue = "asc", name = "order") String order
    ) {
        Pageable pageable01;
        if (order.equals ( "asc" )) {
            pageable01 = PageRequest.of ( page, limit, Sort.by ( sort ).ascending () );
        } else {
            pageable01 = PageRequest.of ( page, limit, Sort.by ( sort ).descending () );
        }
        Page<Book> books = bookService.getByCategoryStatus ( pageable01, true );
        model.addAttribute ( "book", books );
        Pageable pageable02 = PageRequest.of ( 1, 12, Sort.by ( "id" ).ascending () );
        Page<Book> book02 = bookService.getByCategoryStatus ( pageable02, true );
        model.addAttribute ( "book02", book02 );
        Pageable pageable03 = PageRequest.of ( 2, 12, Sort.by ( "id" ).ascending () );
        Page<Book> book03 = bookService.getByCategoryStatus ( pageable03, true );
        model.addAttribute ( "book03", book03 );
        List<Categories> categories = categoriesService.getByStatus ();
        model.addAttribute ( "categories", categories );
        session.setAttribute ( "categories", categories );
        model.addAttribute ( "keyword", keyword );
        return "home/index";
    }


    @RequestMapping("/contact-us")
    public String contactUs() {
        return "home/contact-us";
    }

    @RequestMapping("/about-us")
    public String aboutUs() {
        return "home/about-us";
    }

}
