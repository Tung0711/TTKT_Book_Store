package conn.ra.service.impl;

import conn.ra.model.dto.Response.CheckOutInforDTO;
import conn.ra.model.dto.request.ShoppingCartRequest;
import conn.ra.model.entity.*;
import conn.ra.model.enums.EOrder;
import conn.ra.repository.OrderDetailRepository;
import conn.ra.repository.OrderRepository;
import conn.ra.repository.ShoppingCartRepository;
import conn.ra.security.UserDetail.UserLoggedIn;
import conn.ra.service.BookService;
import conn.ra.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserLoggedIn userLoggedIn;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<ShoppingCart> getAllByUser(User user) {
        return shoppingCartRepository.getAllByUsers ( user );
    }

    @Override
    public ShoppingCart add(ShoppingCartRequest shoppingCartRequest, User user) {
        Book books = bookService.findById ( shoppingCartRequest.getBookId () );
        ShoppingCart shoppingCart = ShoppingCart.builder ()
                .orderQuantity ( shoppingCartRequest.getOrderQuantity () )
                .books ( books )
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
    public ShoppingCart findByUsersAndBook(User user, Book books) {
        return shoppingCartRepository.findByUsersAndBooks ( user, books );
    }

    @Override
    public void deleteByUser(User user) {
        shoppingCartRepository.deleteByUsers ( user );
    }

    @Transactional
    @Override
    public void checkOut(CheckOutInforDTO checkOutInfor) {
        User user = userLoggedIn.getUserLoggedIn ();
        Orders orders = new Orders ();
        orders.setUsers ( user );
        orders.setReceiveName ( checkOutInfor.getReceiveName () );
        orders.setReceivePhone ( checkOutInfor.getReceivePhone () );
        orders.setReceiveAddress ( checkOutInfor.getReceiveAddress () );
        orders.setPaymentMethods ( checkOutInfor.getPaymentMethods () );
        orders.setNote ( checkOutInfor.getNote () );
        orders.setSerialNumber ( UUID.randomUUID ().toString () );
        orders.setCreated ( new java.sql.Date ( new java.util.Date ().getTime () ) );
        orders.setStatus ( EOrder.Waiting );
        List<ShoppingCart> carts = shoppingCartRepository.getAllByUsers ( user );
        double totalPrice = carts.stream ()
                .mapToDouble ( shoppingCart -> shoppingCart.getBooks ().getPrice () * shoppingCart.getOrderQuantity () )
                .sum ();
        orders.setTotalPrice ( totalPrice + 15000 );
        orderRepository.save ( orders );
        for (ShoppingCart cart : carts) {
            OrderDetail ordersDetail = new OrderDetail ();
            ordersDetail.setBooks ( cart.getBooks () );
            ordersDetail.setOrderQuantity ( cart.getOrderQuantity () );
            ordersDetail.setOrders ( orders );
            ordersDetail.setPrice ( cart.getBooks ().getPrice () );
            ordersDetail.setCreate ( new java.sql.Date ( new java.util.Date ().getTime () ) );
            orderDetailRepository.save ( ordersDetail );
        }
        deleteByUser ( user );
    }
}
