package conn.ra.service;

import conn.ra.model.dto.request.WishlistRequest;
import conn.ra.model.entity.WishList;

import java.util.List;

public interface WishlistService {
    WishList add(Long userId, WishlistRequest wishlistRequest);

    List<WishList> getAll(Long userId);

    void delete(Long wishlistId, Long userId);

    WishList findByUserAndBook(Long userId, Long bookId);
}
