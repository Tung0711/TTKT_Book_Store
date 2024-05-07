package conn.ra.controller.permitAll;

import conn.ra.model.entity.User;
import conn.ra.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class PermitAllController {
    @Autowired
    private UserService userService;

    @GetMapping("/sign-in")
    public String SignIn() {
        return "home/shop-login";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        User user = new User ();
        model.addAttribute ( "user", user );
        return "home/shop-registration";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute("user") User user) {
        userService.register ( user );
        return "redirect:/sign-in";
    }

    @GetMapping("/index")
    public String Index() {
        return "home/index";
    }

    @GetMapping("/about-us")
    public String AboutUs() {
        return "home/about-us";
    }

    @GetMapping("/contact-us")
    public String ContactUs() {
        return "home/contact-us";
    }
}
