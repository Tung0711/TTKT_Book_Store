package conn.ra.service.impl;

import conn.ra.model.dto.request.ShoppingCartRequest;
import conn.ra.model.entity.Book;
import conn.ra.model.entity.ShoppingCart;
import conn.ra.model.entity.User;
import conn.ra.repository.ShoppingCartRepository;
import conn.ra.service.BookService;
import conn.ra.service.ShoppingCartService;
import conn.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @Override
    public List<ShoppingCart> getAllByUser(User user) {
        return shoppingCartRepository.getAllByUsers ( user );
    }

    @Override
    public ShoppingCart add(ShoppingCartRequest shoppingCartRequest, Long userId) {
        Book book = bookService.findById ( shoppingCartRequest.getBookId () );
        User user = userService.findById ( userId );

        ShoppingCart shoppingCart = ShoppingCart.builder ()
                .orderQuantity ( shoppingCartRequest.getOrderQuantity () )
                .book ( book )
                .users ( user )
                .build ();
        return shoppingCartRepository.save ( shoppingCart );
    }

    @Override
    public ShoppingCart findById(Long id) {
        return shoppingCartRepository.findById ( id ).orElse ( null );
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save ( shoppingCart );
    }

    @Override
    public void delete(Long id) {
        shoppingCartRepository.deleteById ( id );
    }

    @Override
    public ShoppingCart findByUsersAndBook(User user, Book book) {
        return shoppingCartRepository.findByUsersAndBook ( user, book );
    }

    @Override
    public void updateQuantity(Long id, Integer quantity) {
        ShoppingCart shoppingCart = findById ( id );
        shoppingCart.setOrderQuantity ( quantity );
        save ( shoppingCart );
    }
}
