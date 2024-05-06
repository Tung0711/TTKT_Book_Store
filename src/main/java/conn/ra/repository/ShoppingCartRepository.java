package conn.ra.repository;

import conn.ra.model.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    @Query("SELECT s from ShoppingCart s where s.users.id = :id")
    List<ShoppingCart> findByUsers(@Param("id") Long id);

    @Query("SELECT s from ShoppingCart s where s.book.id = :bookId and s.users.id = :userId")
    ShoppingCart findByUserAndBook(@Param("userId") Long userId, @Param("bookId") Long bookId);
}
