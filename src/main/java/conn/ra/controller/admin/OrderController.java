package conn.ra.controller.admin;

import conn.ra.model.entity.OrderDetail;
import conn.ra.model.entity.Orders;
import conn.ra.service.OrderDetailService;
import conn.ra.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/admin-orders")
    public String ordersPage(Model model) {
        List<Orders> orders = orderService.findAll ();
        model.addAttribute ( "orders", orders );
        return "/admin/admin-orders";
    }

    @GetMapping("/admin-order-details/{id}")
    public String ordersDetailsPage(@PathVariable Long id, Model model) {
        List<OrderDetail> orderDetail = orderDetailService.getByOrderId ( id );
        model.addAttribute ( "orderDetail", orderDetail );
        Orders orders = orderService.findById ( id );
        model.addAttribute ( "orders", orders );
        double totalPrice = orderDetail.stream ()
                .mapToDouble ( item -> item.getOrderQuantity () * item.getPrice () )
                .sum ();
        model.addAttribute ( "totalPrice", totalPrice );
        return "/admin/admin-order-details";
    }

    @ResponseBody
    @GetMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam(name = "status") String status) {
        orderService.updateStatus ( id, status );
        return "/admin/admin-orders";
    }

}