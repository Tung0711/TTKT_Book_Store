package conn.ra.controller.admin;

import conn.ra.model.dto.request.BookRequest;
import conn.ra.model.entity.Book;
import conn.ra.model.entity.Categories;
import conn.ra.service.BookService;
import conn.ra.service.CategoriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BookController {
    @Value("${path-upload}")
    private String pathUpload;
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/admin-books")
    public String book(Model model){
        List<Book> books = bookService.getAll ();
        model.addAttribute ( "books", books );
        return "admin/admin-books";
    }

    @GetMapping("/admin-add-book")
    public String add(Model model, Pageable pageable) {
        List<Categories> categories = categoriesService.getAll ();
        model.addAttribute ( "categories", categories );
        BookRequest bookRequest = new BookRequest ();
        model.addAttribute ( "bookRequest", bookRequest );
        return "admin/admin-add-book";
    }

    @PostMapping("/admin-add-book")
    public String create(@Valid @ModelAttribute("book") BookRequest bookRequest,
                         @RequestParam("imgBook") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename ();
        try {
            FileCopyUtils.copy ( file.getBytes (), new File ( pathUpload + fileName ) );
            // lưu tên file vào database
            bookRequest.setImage ( fileName );
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
        bookService.add ( bookRequest );
        return "redirect:/admin/admin-books";
    }

    @GetMapping("/admin-edit-book/{id}")
    public String edit(@PathVariable Long id, Model model, Pageable pageable) {
        List<Categories> categories = categoriesService.getAll ();
        model.addAttribute ( "categories", categories );
        Book books = bookService.findById ( id );
        model.addAttribute ( "books", books );
        return "admin/admin-edit-book";
    }

    @PostMapping("/admin-edit-book/{id}")
    public String update(@ModelAttribute("book") Book books, @RequestParam("imgBook") MultipartFile file) {
        String fileName = file.getOriginalFilename ();
        try {
            FileCopyUtils.copy ( file.getBytes (), new File ( pathUpload + fileName ) );
            // lưu tên file vào database
            books.setImages ( fileName );
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
        bookService.edit ( books );
        return "redirect:/admin/admin-books";
    }

    @GetMapping("/book/status/{id}")
    public String updateStatus(@PathVariable("id") Long id) {
        Book books = bookService.findById ( id );
        books.setStatus ( !books.getStatus () );
        bookService.edit ( books );
        return "redirect:/admin/admin-books";
    }

    @GetMapping("/delete-book/{id}")
    public String delete(@PathVariable Long id) {
        Book books = bookService.findById ( id );
        books.setStatus ( !books.getStatus () );
        bookService.edit ( books );
        return "redirect:/admin/admin-books";
    }
}