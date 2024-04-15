package conn.ra.service;

import conn.ra.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book findById(Long id);

    Boolean add(Book book);
    Boolean edit(Book book);

    void delete(Long id);
}
