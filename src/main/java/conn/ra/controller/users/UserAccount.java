package conn.ra.controller.users;

import conn.ra.model.dto.request.AddressRequest;
import conn.ra.model.dto.request.UserRegister;
import conn.ra.model.entity.Address;
import conn.ra.model.entity.Orders;
import conn.ra.model.entity.User;
import conn.ra.model.entity.WishList;
import conn.ra.security.UserDetail.UserLoggedIn;
import conn.ra.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserAccount {
    @Autowired
    private UserService userService;

    @PostMapping("/update-profile/{id}")
    public String updateAcc(@ModelAttribute("users") UserRegister user, @PathVariable("id") Long id) {
        userService.updateAcc ( user, id );
        return "redirect:/user/user-dashboard";
    }
}
