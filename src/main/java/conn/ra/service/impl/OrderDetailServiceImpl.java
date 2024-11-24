package conn.ra.service.impl;

import conn.ra.model.entity.Book;
import conn.ra.model.entity.OrderDetail;
import conn.ra.model.entity.Orders;
import conn.ra.repository.OrderDetailRepository;
import conn.ra.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId ( orderId );
    }

    @Override
    public OrderDetail add(Book books, Orders orders, int orderQuantity) {
        OrderDetail orderDetail = OrderDetail.builder ()
                .orders ( orders )
                .books ( books )
                .price ( books.getPrice () )
                .orderQuantity ( orderQuantity )
                .create ( new java.sql.Date ( new java.util.Date ().getTime () ) )
                .build ();
        return orderDetailRepository.save ( orderDetail );
    }

    @Override
    public List<OrderDetail> findByOrder(Orders orders) {
        return orderDetailRepository.findAllByOrders ( orders );
    }
}
