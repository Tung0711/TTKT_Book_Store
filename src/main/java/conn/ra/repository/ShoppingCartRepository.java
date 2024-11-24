package conn.ra.repository;

import conn.ra.model.entity.Book;
import conn.ra.model.entity.ShoppingCart;
import conn.ra.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> getAllByUsers(User user);

    ShoppingCart findByUsersAndBooks(User users, Book books);

    void deleteByUsers(User user);
}
