package conn.ra.repository;

import conn.ra.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAllByBookNameContainingIgnoreCase(String bookName, Pageable pageable);

    @Query("select b from Book b where b.categories.status = :status")
    Page<Book> findByCatalogStatus(Pageable pageable, Boolean status);

    @Query("select b from Book b where b.categories.id = :id")
    Page<Book> findByCatalogId(Long id, Pageable pageable);

    List<Book> findByStatus(Boolean status);

    @Query("SELECT b FROM Book b WHERE (b.categories.id = :categoryId) AND b.status = true ")
    List<Book> findAllByCategory(Long categoryId);
}
