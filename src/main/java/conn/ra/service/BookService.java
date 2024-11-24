package conn.ra.service;

import conn.ra.model.dto.request.BookRequest;
import conn.ra.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book findById(Long id);

    Book add(BookRequest bookRequest);

    Boolean edit(Book books);

    Book save(Book books);

    void delete(Long id);

    Page<Book> getByCategoryStatus(Pageable pageable, Boolean status);

    Page<Book> getByCategoryId(Long id, Pageable pageable);

    List<Book> getByStatus();

    Page<Book> searchByName(String name, Pageable pageable);

    List<Book> findByCategory(Long categoryId);
}
