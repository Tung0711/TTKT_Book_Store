package conn.ra.repository;

import conn.ra.model.entity.Book;
import conn.ra.model.entity.ShoppingCart;
import conn.ra.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> getAllByUsers(User user);

    ShoppingCart findByUsersAndBook(User users, Book book);

}
