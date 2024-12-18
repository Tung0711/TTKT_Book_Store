package conn.ra.service;

import conn.ra.model.entity.Orders;
import conn.ra.model.entity.User;
import conn.ra.model.enums.EOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    List<Orders> findAll();

    List<Orders> getAll(User user);

    Orders add(User users, Double totalPrice);

    Orders getBySerial(Long userId, String serial);

    List<Orders> getByStatus(Long userId, EOrder status);

    Orders save(Orders orders);

    Orders findById(Long id);

    Orders getByIdAndStatus(Long userId, Long orderId, EOrder status);

    void updateStatus(Long id, String status);
}
