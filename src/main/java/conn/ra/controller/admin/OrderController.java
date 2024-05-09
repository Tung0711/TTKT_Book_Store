package conn.ra.controller.admin;

import conn.ra.model.entity.OrderDetail;
import conn.ra.model.entity.Orders;
import conn.ra.service.OrderDetailService;
import conn.ra.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private OrderService orderService;
    private OrderDetailService orderDetailService;

    @GetMapping("/admin-orders")
    public String ordersPage(Model model) {
        List<Orders> orders = orderService.findAll ();
        model.addAttribute ( "orders", orders );
        return "/admin/admin-orders";
    }

    @GetMapping("/orders_details/{id}")
    public String ordersDetailsPage(@PathVariable Long id,Model model) {
        List<OrderDetail> orderDetails=orderDetailService.getByOrderId ( id );
        model.addAttribute ( "orderDetail",orderDetails );
        Orders orders = orderService.findById ( id );
        model.addAttribute ( "orders",orders );
        return "/admin/admin-order-details";
    }
}