package conn.ra.controller.admin;

import conn.ra.model.dto.request.InvoiceDetailRequest;
import conn.ra.model.dto.request.InvoiceRequest;
import conn.ra.model.entity.*;
import conn.ra.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceDetailService invoiceDetailService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/admin-invoice")
    public String invoicePage(Model model) {
        List<Invoice> invoice = invoiceService.findAll ();
        model.addAttribute ( "invoice", invoice );
        return "/admin/admin-invoice";
    }

    @GetMapping("/admin-invoice-details/{id}")
    public String invoiceDetailsPage(@PathVariable Long id, Model model, HttpSession session) {
        List<InvoiceDetail> invoiceDetail = invoiceDetailService.getByInvoiceId ( id );
        session.setAttribute ( "id", id );
        model.addAttribute ( "invoiceDetail", invoiceDetail );
        Invoice invoice = invoiceService.findById ( id );
        model.addAttribute ( "invoice", invoice );
        Double totalPrice = invoiceDetailService.totalPrice ( id );
        model.addAttribute ( "totalPrice", totalPrice );
        return "/admin/admin-invoice-details";
    }

    @GetMapping("/admin-add-invoice")
    public String addInvoice(Model model) {
        List<Vendor> vendors = vendorService.getByStatus ();
        model.addAttribute ( "vendor", vendors );
        InvoiceRequest invoiceRequest = new InvoiceRequest ();
        model.addAttribute ( "invoiceRequest", invoiceRequest );
        return "admin/admin-add-invoice";
    }

    @PostMapping("/admin-add-invoice")
    public String addInvoice(@Valid @ModelAttribute("invoiceRequest") InvoiceRequest invoiceRequest) {
        invoiceService.addInvoice ( invoiceRequest );
        return "redirect:/admin/admin-invoice";
    }

    @GetMapping("/admin-add-invoiceDetail")
    public String addInvoiceDetail(Model model, HttpSession session,
                                   @RequestParam(name = "brandId", required = false) Long brandId,
                                   @RequestParam(name = "categoryId", required = false) Long categoryId) {
        List<Categories> categories = categoriesService.getByStatus ();
        model.addAttribute ( "categories", categories );
        Long id = (Long) session.getAttribute ( "id" );
        model.addAttribute ( "id", id );
        InvoiceDetailRequest invoiceDetailRequest = new InvoiceDetailRequest ();
        model.addAttribute ( "invoiceDetailRequest", invoiceDetailRequest );
        List<Book> books = bookService.findByCategory ( categoryId );
        model.addAttribute ( "books", books );
        model.addAttribute ( "brandId", brandId );
        model.addAttribute ( "categoryId", categoryId );
        return "admin/admin-add-invoiceDetail";
    }

    @PostMapping("/admin-add-invoiceDetail")
    public String addInvoiceDetail(@Valid @ModelAttribute("invoiceDetailRequest") InvoiceDetailRequest invoiceDetailRequest,
                                   HttpSession session, Model model,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        Long id = (Long) session.getAttribute ( "id" );
        List<Book> books = bookService.getByStatus ();
        model.addAttribute ( "books", books );
        if (bindingResult.hasErrors ()) {
            return "admin/admin-add-invoiceDetail";
        }
        invoiceDetailService.addInvoiceDetail ( invoiceDetailRequest, id );
        redirectAttributes.addAttribute ( "id", id );
        return "redirect:/admin/admin-invoice-details/{id}";
    }

    @GetMapping("/invoiceConfirm/{id}")
    public String invoiceConfirm(@PathVariable Long id, RedirectAttributes redirAttrs) {
        Boolean invoiceConfirm = invoiceService.invoiceConfirm ( id );
        if (!invoiceConfirm) {
            redirAttrs.addFlashAttribute ( "error", "Phiếu nhập hàng chưa có sản phẩm!" );
            return "redirect:/admin/admin-invoice";
        } else {
            redirAttrs.addFlashAttribute ( "success", "Duyệt phiếu nhập hàng thành công!" );
            return "redirect:/admin/admin-invoice";
        }
    }
}
