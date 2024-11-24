package conn.ra.controller.users;

import conn.ra.model.dto.Response.CheckOutInforDTO;
import conn.ra.model.entity.Address;
import conn.ra.model.entity.ShoppingCart;
import conn.ra.model.entity.User;
import conn.ra.model.enums.PaymentMethods;
import conn.ra.security.UserDetail.UserLoggedIn;
import conn.ra.service.AddressService;
import conn.ra.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class CheckoutController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserLoggedIn userLoggedIn;
    @Autowired
    private AddressService addressService;

    @GetMapping("/shop-checkout")
    public String checkOut(Model model, RedirectAttributes redirAttrs) {
        User user = userLoggedIn.getUserLoggedIn ();
        model.addAttribute ( "user", user );
        List<ShoppingCart> carts = shoppingCartService.getAllByUser ( user );
        if (carts.isEmpty ()) {
            redirAttrs.addFlashAttribute ( "error", "Không có sản phẩm trong giỏ hàng!" );
            return "redirect:/user/shop-cart";
        }
        if (addressService.getAllByUser ( user ).isEmpty ()) {
            redirAttrs.addFlashAttribute ( "error", "Chưa có địa chỉ nhận hàng! Hãy thêm địa chỉ nhận hàng rồi mới thanh toán!" );
            return "redirect:/user/shop-cart";
        } else {
            List<Address> addresses = addressService.getAllByUser ( user );
            model.addAttribute ( "carts", carts );
            model.addAttribute ( "addresses", addresses );
            double totalPrice = (double) 0;
            for (ShoppingCart cart : carts) {
                totalPrice += (cart.getBooks ().getPrice () * cart.getOrderQuantity ());
            }
            model.addAttribute ( "totalPrice", totalPrice );
            CheckOutInforDTO checkOutInfor = new CheckOutInforDTO ();
            model.addAttribute ( "checkOutInfor", checkOutInfor );
            return "home/shop-checkout";
        }
    }

    @PostMapping("/shop-checkout")
    public String checkOut(@ModelAttribute("checkOutInfor") CheckOutInforDTO checkOutInfor, RedirectAttributes redirAttrs) {
        if (checkOutInfor.getReceiveName ().isEmpty ()) {
            redirAttrs.addFlashAttribute ( "error", "Chọn địa chỉ nhận hàng trước khi thanh toán!" );
            return "redirect:/user/shop-checkout";
        } else {
            checkOutInfor.setPaymentMethods ( PaymentMethods.COD );
            shoppingCartService.checkOut ( checkOutInfor );
            return "redirect:/index";
        }
    }
}
