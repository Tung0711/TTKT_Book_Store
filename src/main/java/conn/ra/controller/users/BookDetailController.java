package conn.ra.controller.users;

import conn.ra.model.dto.request.ShoppingCartRequest;
import conn.ra.model.entity.Book;
import conn.ra.model.entity.Categories;
import conn.ra.service.BookService;
import conn.ra.service.CategoriesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class BookDetailController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/books-detail/{id}")
    public String bookDetail(Model model, @PathVariable("id") Long id) {
        ShoppingCartRequest shoppingCartRequest = new ShoppingCartRequest ();
        shoppingCartRequest.setOrderQuantity ( 1 );
        Book book = bookService.findById ( id );
        model.addAttribute ( "book", book );
        model.addAttribute ( "shoppingCartRequest", shoppingCartRequest );
        return "/home/books-detail";
    }

    @GetMapping("/books-grid-view")
    public String Home(HttpSession session, Model model,
                       @RequestParam(defaultValue = "12", name = "limit") int limit,
                       @RequestParam(defaultValue = "0", name = "page") int page,
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
        return "/home/books-grid-view";
    }
}
