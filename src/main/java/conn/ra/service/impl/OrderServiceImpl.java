package conn.ra.service.impl;

import conn.ra.model.entity.Book;
import conn.ra.model.entity.OrderDetail;
import conn.ra.model.entity.Orders;
import conn.ra.model.entity.User;
import conn.ra.model.enums.EOrder;
import conn.ra.repository.OrderDetailRepository;
import conn.ra.repository.OrderRepository;
import conn.ra.service.BookService;
import conn.ra.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private BookService bookService;

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll ();
    }

    @Override
    public List<Orders> getAll(User user) {
        return orderRepository.findAllByUsers ( user );
    }

    @Override
    public Orders add(User users, Double totalPrice) {
        Orders order = Orders.builder ()
                .serialNumber ( UUID.randomUUID ().toString () )
                .users ( users )
                .totalPrice ( totalPrice )
                .status ( EOrder.Waiting )
                .created ( new java.sql.Date ( new java.util.Date ().getTime () ) )
                .receiveName ( users.getFullName () )
                .receiveAddress ( users.getAddress () )
                .receivePhone ( users.getPhone () )
                .build ();
        return orderRepository.save ( order );
    }

    @Override
    public Orders getBySerial(Long userId, String serial) {
        return orderRepository.findByUserIdAndSerial ( userId, serial );
    }

    @Override
    public List<Orders> getByStatus(Long userId, EOrder status) {
        return orderRepository.findByUserIdAndStatus ( userId, status );
    }

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save ( orders );
    }

    @Override
    public Orders findById(Long id) {
        return orderRepository.findById ( id ).orElse ( null );
    }

    @Override
    public Orders getByIdAndStatus(Long userId, Long orderId, EOrder status) {
        return orderRepository.findByIdAndStatus ( userId, orderId, status );
    }

    @Override
    public void updateStatus(Long id, String status) {
        Orders orders = findById ( id );
        switch (status) {
            case "Waiting" -> orders.setStatus ( EOrder.Waiting );
            case "Confirm" -> orders.setStatus ( EOrder.Confirm );
            case "Delivery" -> orders.setStatus ( EOrder.Delivery );
            case "Success" -> {
                orders.setStatus ( EOrder.Success );
                List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId ( orders.getId () );
                for (OrderDetail orderDetail : orderDetails) {
                    Book books = bookService.findById ( orderDetail.getBooks ().getId () );
                    books.setStockQuantity ( books.getStockQuantity () - orderDetail.getOrderQuantity () );
                    bookService.edit ( books );
                }

            }
            case "Cancel" -> orders.setStatus ( EOrder.Cancel );
        }
        orderRepository.save ( orders );
    }
}
