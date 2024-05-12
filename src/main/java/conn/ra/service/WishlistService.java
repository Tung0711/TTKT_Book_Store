package conn.ra.service;

import conn.ra.model.dto.request.WishlistRequest;
import conn.ra.model.entity.Book;
import conn.ra.model.entity.User;
import conn.ra.model.entity.WishList;

import java.util.List;

public interface WishlistService {
    WishList add(User user, WishlistRequest wishlistRequest);

    List<WishList> getAll(User user);

    void delete(Long wishlistId, User user);

    WishList findByUserAndBook(User user, Book book);
}
