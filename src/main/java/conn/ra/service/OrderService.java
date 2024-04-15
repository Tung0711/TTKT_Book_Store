package conn.ra.service;

import conn.ra.model.entity.Orders;
import conn.ra.model.entity.User;
import conn.ra.model.enums.EOrder;

import java.util.List;

public interface OrderService {
    List<Orders> findAll();
    List<Orders> getAll(Long userId);
    Orders add(User users, Double totalPrice);
    Orders getBySerial(Long userId, String serial);
    List<Orders> getByStatus(Long userId, EOrder status);
    Orders save(Orders orders);
    Orders getByIdAndStatus(Long userId, Long orderId, EOrder status);
}
