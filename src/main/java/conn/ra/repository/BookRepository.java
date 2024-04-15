package conn.ra.repository;

import conn.ra.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT bk from Book bk WHERE bk.bookName like %?1% ")
    List<Book> findByNameOrDescription(String bookName, String bookDescription);
    boolean existsByBookName(String bookName);
    @Query("select bk from Book bk where bk.categories.status = :status")
    Page<Book> findByCatalogStatus(Pageable pageable, Boolean status);
    @Query("select bk from Book bk where bk.categories.id = :id")
    List<Book> findByCatalogId(Long id);
}
