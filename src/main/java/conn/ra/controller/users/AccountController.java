package conn.ra.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class AccountController {
    @GetMapping("/my-profile")
    public String MyProfile() {
        return "home/my-profile";
    }
}
