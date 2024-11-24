package conn.ra.controller.admin;

import conn.ra.model.dto.request.UserRegister;
import conn.ra.model.entity.Categories;
import conn.ra.model.entity.User;
import conn.ra.security.UserDetail.UserLoggedIn;
import conn.ra.service.RoleService;
import conn.ra.service.UserService;
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
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserLoggedIn userLoggedIn;

    @GetMapping("/admin-users")
    public String users(Model model) {
        List<User> users = userService.getAll ();
        model.addAttribute ( "user", users );
        return "admin/admin-users";
    }

    @GetMapping("/user/status/{id}")
    public String updateStatus(@PathVariable("id") Long id) {
        User user = userService.findById ( id );
        user.setStatus ( !user.getStatus () );
        userService.save ( user );
        return "redirect:/admin/admin-users";
    }

    @GetMapping("/profile-setting")
    public String profileSetting(Model model) {
        User user = userLoggedIn.getUserLoggedIn ();
        model.addAttribute ( "user", user );
        return "admin/profile-setting";
    }

    @GetMapping("/edit-profile")
    public String edit(Model model) {
        User user = userLoggedIn.getUserLoggedIn ();
        model.addAttribute ( "user", user );
        return "admin/edit-profile";
    }

    @PostMapping("/edit-profile")
    public String update(@ModelAttribute("User") UserRegister user, Long id) {
        userService.updateAcc ( user, id );
        return "redirect:/admin/profile-setting";
    }
}
