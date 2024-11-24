package conn.ra.controller.users;

import conn.ra.model.dto.request.ShoppingCartRequest;
import conn.ra.model.entity.Book;
import conn.ra.model.entity.Categories;
import conn.ra.service.BookService;
import conn.ra.service.CategoriesService;
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
    public String bookThumbnail(Model model,
                                @PathVariable("id") Long id,
                                @RequestParam(defaultValue = "4", name = "limit") int limit,
                                @RequestParam(defaultValue = "1", name = "page") int page,
                                @RequestParam(defaultValue = "id", name = "sort") String sort,
                                @RequestParam(defaultValue = "asc", name = "order") String order
    ) {
        Pageable pageable;
        if (order.equals ( "asc" )) {
            pageable = PageRequest.of ( page, limit, Sort.by ( sort ).ascending () );
        } else {
            pageable = PageRequest.of ( page, limit, Sort.by ( sort ).descending () );
        }
        Page<Book> book = bookService.getByCategoryStatus ( pageable, true );
        model.addAttribute ( "book", book );
        Pageable pageable02 = PageRequest.of ( 0, 12, Sort.by ( "id" ).ascending () );
        Page<Book> book02 = bookService.getByCategoryStatus ( pageable02, true );
        model.addAttribute ( "book02", book02 );

        ShoppingCartRequest shoppingCartRequest = new ShoppingCartRequest ();
        shoppingCartRequest.setOrderQuantity ( 1 );
        Book books = bookService.findById ( id );
        model.addAttribute ( "books", books );
        model.addAttribute ( "shoppingCartRequest", shoppingCartRequest );
        return "home/books-detail";
    }

    @GetMapping("/books-grid-view")
    public String shopLeftSidebar(Model model,
                                  @RequestParam(defaultValue = "12", name = "limit") int limit,
                                  @RequestParam(defaultValue = "0", name = "page") int page,
                                  @RequestParam(defaultValue = "id", name = "sort") String sort,
                                  @RequestParam(defaultValue = "asc", name = "order") String order,
                                  @RequestParam(name = "keyword", required = false) String keyword
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
        model.addAttribute ( "categories", categories );
        model.addAttribute ( "totalProducts", books.getTotalElements () );
        model.addAttribute ( "currentPage", books.getNumber () );
        model.addAttribute ( "totalPage", books.getTotalPages () );
        model.addAttribute ( "keyword", keyword );
        model.addAttribute ( "sort", sort );
        model.addAttribute ( "order", order );
        return "home/books-grid-view";
    }
    @GetMapping("/books-list")
    public String shopList(Model model,
                                  @RequestParam(defaultValue = "12", name = "limit") int limit,
                                  @RequestParam(defaultValue = "0", name = "page") int page,
                                  @RequestParam(defaultValue = "id", name = "sort") String sort,
                                  @RequestParam(defaultValue = "asc", name = "order") String order,
                                  @RequestParam(name = "keyword", required = false) String keyword
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
        model.addAttribute ( "categories", categories );
        model.addAttribute ( "totalProducts", books.getTotalElements () );
        model.addAttribute ( "currentPage", books.getNumber () );
        model.addAttribute ( "totalPage", books.getTotalPages () );
        model.addAttribute ( "keyword", keyword );
        model.addAttribute ( "sort", sort );
        model.addAttribute ( "order", order );
        return "home/books-list";
    }
}
