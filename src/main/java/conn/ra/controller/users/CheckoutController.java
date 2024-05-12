package conn.ra.controller.users;

import conn.ra.model.entity.Book;
import conn.ra.model.entity.Orders;
import conn.ra.model.entity.ShoppingCart;
import conn.ra.model.entity.User;
import conn.ra.security.UserDetail.UserLoggedIn;
import conn.ra.service.OrderDetailService;
import conn.ra.service.OrderService;
import conn.ra.service.ShoppingCartService;
import conn.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class CheckoutController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserLoggedIn userLoggedIn;
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/shop-checkout")
    private String shopCheckOut(Model model) {
        User user = userLoggedIn.getUserLoggedIn ();
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAllByUser ( user );
        double total = 0;
        for (ShoppingCart item : shoppingCarts) {
            total += item.getBook ().getPrice () * item.getOrderQuantity ();
        }
        model.addAttribute ( "shoppingCart", shoppingCarts );
        model.addAttribute ( "user", user );
        model.addAttribute ( "total", total );
        return "home/shop-checkout";
    }
    @PostMapping("/shop-checkout")
    public String checkOut(@ModelAttribute("user") User newUser) {
        User user = userLoggedIn.getUserLoggedIn ();
        //  lấy ra giỏ hàng của tài khoản đang đăng nhập
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAllByUser (user);
        //  lấy ra thông tin tài khoản đang đăng nhập
        user.setFullName(newUser.getFullName());
        user.setAddress(newUser.getAddress());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());
        //  tổng tiền cần thanh toán
        double totalPrice = shoppingCarts.stream()
                .mapToDouble(shoppingCart -> shoppingCart.getBook ().getPrice() * shoppingCart.getOrderQuantity ())
                .sum();
        //  tạo mới order
        Orders order = orderService.add(user, totalPrice);
        //  tạo mới order detail
        for (ShoppingCart shoppingCart: shoppingCarts) {
            int orderQuantity = shoppingCart.getOrderQuantity ();
            Book book = shoppingCart.getBook ();
            orderDetailService.add(book, order, orderQuantity);
        }
        //  Xóa tất cả sản phẩm khỏi giỏ hàng
        shoppingCarts.forEach(shoppingCart -> shoppingCartService.delete(shoppingCart.getId()));

        return "redirect:/index";
    }
}
