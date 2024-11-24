package conn.ra.controller.users;

import conn.ra.model.dto.request.AddressRequest;
import conn.ra.model.entity.User;
import conn.ra.security.UserDetail.UserLoggedIn;
import conn.ra.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserLoggedIn userLoggedIn;

    @PostMapping("/add-address")
    public String addAddress(@ModelAttribute("address") AddressRequest address) {
        addressService.add ( address );
        return "redirect:/user/user-dashboard";
    }

    @PostMapping("/edit-address/{id}")
    public String updateAddress(@ModelAttribute("address") AddressRequest address, @PathVariable("id") Long id) {
        addressService.edit ( address, id );
        return "redirect:/user/user-dashboard";
    }

    @GetMapping("/delete-address/{id}")
    public String delete(@PathVariable("id") Long addressId) {
        User user = userLoggedIn.getUserLoggedIn ();
        addressService.delete ( addressId, user );
        return "redirect:/user/user-dashboard";
    }
}
