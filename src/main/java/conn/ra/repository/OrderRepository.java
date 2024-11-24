package conn.ra.repository;

import conn.ra.model.entity.Orders;
import conn.ra.model.entity.User;
import conn.ra.model.enums.EOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByUsers(User user);

    @Query("select o from Orders o where o.users.id = :userId and o.serialNumber = :serial")
    Orders findByUserIdAndSerial(Long userId, String serial);

    @Query("select o from Orders o where o.users.id = :userId and o.status = :status")
    List<Orders> findByUserIdAndStatus(Long userId, EOrder status);

    @Query("select o from Orders o where o.users.id = :userId and o.status = :status and o.id = :orderId")
    Orders findByIdAndStatus(Long userId, Long orderId, EOrder status);
}
