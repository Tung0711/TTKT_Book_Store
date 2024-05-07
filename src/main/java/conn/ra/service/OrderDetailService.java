package conn.ra.service;

import conn.ra.model.entity.Book;
import conn.ra.model.entity.OrderDetail;
import conn.ra.model.entity.Orders;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getByOrderId(Long orderId);

    OrderDetail add(Book book, Orders orders, int orderQuantity);
}