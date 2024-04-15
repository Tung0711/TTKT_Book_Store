package conn.ra.controller.admin;

import conn.ra.model.entity.User;
import conn.ra.service.RoleService;
import conn.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/admin-users")
    public String users(Model model, Pageable pageable) {
        Page<User> users = userService.getAll (pageable);
        model.addAttribute ( "user",users);
        return "admin/admin-users";
    }

}
