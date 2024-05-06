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

    @RequestMapping("/privacy-policy")
    public String privacyPolicy() {
        return "admin/privacy-policy";
    }

    @RequestMapping("/terms-of-service")
    public String termsOfService() {
        return "admin/terms-of-service";
    }
}
