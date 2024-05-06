package conn.ra.service.impl;

import conn.ra.model.entity.Book;
import conn.ra.repository.BookRepository;
import conn.ra.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Boolean add(Book book) {
        try {
            bookRepository.save ( book );
            return true;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return false;
    }

    @Override
    public Boolean edit(Book book) {
        try {
            bookRepository.save ( book );
            return true;
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return false;
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById ( id );
    }
}
