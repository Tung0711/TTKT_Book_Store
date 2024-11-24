package conn.ra.service;

import conn.ra.model.dto.Response.CheckOutInforDTO;
import conn.ra.model.dto.request.ShoppingCartRequest;
import conn.ra.model.entity.Book;
import conn.ra.model.entity.ShoppingCart;
import conn.ra.model.entity.User;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getAllByUser(User user);

    ShoppingCart add(ShoppingCartRequest shoppingCartRequest, User user);

    ShoppingCart findById(Long id);

    ShoppingCart save(ShoppingCart shoppingCart);

    void delete(Long id);

    ShoppingCart findByUsersAndBook(User user, Book books);

    void checkOut(CheckOutInforDTO checkOutInforDTO);

    void deleteByUser(User user);
}
