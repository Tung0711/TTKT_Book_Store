package conn.ra.service.impl;

import conn.ra.model.dto.request.BookRequest;
import conn.ra.model.entity.Book;
import conn.ra.repository.BookRepository;
import conn.ra.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll ();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById ( id ).orElse ( null );
    }

    @Override
    public Book add(BookRequest bookRequest) {
        Book books = new Book ();
        books.setBookName ( bookRequest.getBookName () );
        books.setAuthor ( bookRequest.getAuthor () );
        books.setDescription ( bookRequest.getDescription () );
        books.setImages ( bookRequest.getImage () );
        books.setPrice ( bookRequest.getUnitPrice () );
        books.setCategories ( bookRequest.getCategories () );
        books.setStatus ( true );
        return bookRepository.save ( books );
    }

    @Override
    public Boolean edit(Book books) {
        try {
            bookRepository.save ( books );
            return true;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return false;
    }

    @Override
    public Book save(Book books) {
        return bookRepository.save ( books );
    }


    @Override
    public void delete(Long id) {
        bookRepository.deleteById ( id );
    }

    @Override
    public Page<Book> getByCategoryStatus(Pageable pageable, Boolean status) {
        return bookRepository.findByCatalogStatus ( pageable, status );
    }

    @Override
    public Page<Book> getByCategoryId(Long id, Pageable pageable) {
        return bookRepository.findByCatalogId ( id, pageable );
    }

    @Override
    public List<Book> getByStatus() {
        return bookRepository.findByStatus ( true );
    }

    @Override
    public Page<Book> searchByName(String name, Pageable pageable) {
        return bookRepository.findAllByBookNameContainingIgnoreCase ( name, pageable );
    }

    @Override
    public List<Book> findByCategory(Long categoryId) {
        return bookRepository.findAllByCategory ( categoryId );
    }
}
