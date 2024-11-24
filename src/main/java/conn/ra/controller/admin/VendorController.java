package conn.ra.controller.admin;

import conn.ra.model.entity.Vendor;
import conn.ra.service.VendorService;
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
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @GetMapping("/admin-vendor")
    public String vendor(Model model){
        List<Vendor> vendors = vendorService.getAll ();
        model.addAttribute ( "vendor", vendors );

        return "admin/admin-vendor";
    }

    @GetMapping("/admin-add-vendor")
    public String save(Model model) {
        Vendor vendor = new Vendor ();
        vendor.setStatus ( true );
        model.addAttribute ( "vendor", vendor );
        return "admin/admin-add-vendor";
    }

    @PostMapping("/admin-add-vendor")
    public String create(@ModelAttribute("vendor") Vendor vendor) {
        vendorService.add ( vendor );
        return "redirect:/admin/admin-vendor";
    }

    @GetMapping("/vendor/status/{id}")
    public String updateStatus(@PathVariable("id") Long id) {
        Vendor vendor = vendorService.findById ( id );
        vendor.setStatus ( !vendor.getStatus () );
        vendorService.edit ( vendor );
        return "redirect:/admin/admin-vendor";
    }

    @GetMapping("/admin-edit-vendor/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Vendor vendor = vendorService.findById ( id );
        model.addAttribute ( "vendor", vendor );
        return "admin/admin-edit-vendor";
    }

    @PostMapping("/admin-edit-vendor/{id}")
    public String update(@ModelAttribute("vendor") Vendor vendor) {
        vendorService.edit ( vendor );
        return "redirect:/admin/admin-vendor";
    }

    @GetMapping("/delete-vendor/{id}")
    public String delete(@PathVariable Long id) {
        Vendor vendor = vendorService.findById ( id );
        vendor.setStatus ( !vendor.getStatus () );
        vendorService.edit ( vendor );
        return "redirect:/admin/admin-vendor";
    }
}