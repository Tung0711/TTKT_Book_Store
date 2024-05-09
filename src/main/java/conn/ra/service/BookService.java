package conn.ra.service;

import conn.ra.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book findById(Long id);

    Boolean add(Book book);
    Boolean edit(Book book);

    void delete(Long id);
    Page<Book> getByCategoryStatus(Pageable pageable, Boolean status);
}
