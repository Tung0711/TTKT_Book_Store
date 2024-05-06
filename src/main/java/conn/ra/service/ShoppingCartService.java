package conn.ra.service;

import conn.ra.model.dto.request.ShoppingCartRequest;
import conn.ra.model.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getAll(Long userId);
    ShoppingCart add(ShoppingCartRequest shoppingCartRequest, Long userId);
    ShoppingCart findById(Long id);
    ShoppingCart save(ShoppingCart shoppingCart);
    void delete(Long id);
    ShoppingCart findByBookId(Long userId, Long bookId);
}
