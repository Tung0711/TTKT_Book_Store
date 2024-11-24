package conn.ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    @RequestMapping("/admin-dashboard")
    public String dashboard() {
        return "admin/admin-dashboard";
    }

}
