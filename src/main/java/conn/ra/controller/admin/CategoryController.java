package conn.ra.controller.admin;

import conn.ra.model.entity.Categories;
import conn.ra.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/admin-category")
    public String categories(Model model) {
        List<Categories> categories = categoriesService.getAll ();
        model.addAttribute ( "Categories", categories );
        return "admin/admin-category";
    }

    @GetMapping("/admin-add-category")
    public String save(Model model) {
        Categories categories = new Categories ();
        categories.setStatus ( true );
        model.addAttribute ( "Categories", categories );
        return "admin/admin-add-category";
    }

    @PostMapping("/admin-add-category")
    public String create(@ModelAttribute("Categories") Categories categories) {
        categoriesService.add ( categories );
        return "redirect:/admin/admin-category";
    }

    @GetMapping("/category/status/{id}")
    public String updateStatus(@PathVariable("id") Long id) {
        Categories categories = categoriesService.findById ( id );
        categories.setStatus ( !categories.getStatus () );
        categoriesService.edit ( categories );
        return "redirect:/admin/admin-category";
    }

    @GetMapping("/admin-edit-category/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Categories categories = categoriesService.findById ( id );
        model.addAttribute ( "Categories", categories );
        return "admin/admin-edit-category";
    }

    @PostMapping("/admin-edit-category/{id}")
    public String update(@ModelAttribute("Categories") Categories categories) {
        categoriesService.edit ( categories );
        return "redirect:/admin/admin-category";
    }

    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable Long id) {
        Categories categories = categoriesService.findById ( id );
        categories.setStatus ( !categories.getStatus () );
        categoriesService.edit ( categories );
        return "redirect:/admin/admin-category";
    }
}
