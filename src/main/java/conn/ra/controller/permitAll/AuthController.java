package conn.ra.controller.permitAll;

import conn.ra.model.dto.request.UserRegister;
import conn.ra.model.entity.User;
import conn.ra.repository.UserRepository;
import conn.ra.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/sign-in")
    public String SignIn() {
        return "home/shop-login";
    }

    @GetMapping("/sign-in-error")
    public String setErrorMessageLogin(Model model) {
        model.addAttribute ( "errorMessage", "Username or Password is not correct" );
        return "home/shop-login";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        User user = new User ();
        model.addAttribute ( "user", user );
        return "home/shop-registration";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute("user") UserRegister userRegister,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors ()) {
            return "home/shop-registration";
        }
        if (userRepository.existsByUsername ( userRegister.getUsername () )) {
            redirectAttributes.addFlashAttribute ( "errorMessage", "userName is exits" );
            return "redirect:/sign-up";
        }
        userService.register ( userRegister );
        return "redirect:/sign-in";
    }
}
