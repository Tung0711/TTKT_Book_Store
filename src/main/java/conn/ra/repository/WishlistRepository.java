package conn.ra.repository;

import conn.ra.model.entity.Book;
import conn.ra.model.entity.User;
import conn.ra.model.entity.WishList;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface WishlistRepository extends JpaRepository<WishList, Long> {
    List<WishList> getAllByUsers(User user);

    void deleteByIdAndUsers(Long wishlistId, User user);

    WishList findByUsersAndBooks(User user, Book books);
}
