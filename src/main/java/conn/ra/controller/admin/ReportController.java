package conn.ra.controller.admin;


import conn.ra.model.dto.Response.ImportReportDTO;
import conn.ra.model.dto.Response.RevenueReportDTO;
import conn.ra.model.dto.Response.SalesReportDTO;
import conn.ra.model.entity.Categories;
import conn.ra.model.entity.Vendor;
import conn.ra.service.CategoriesService;
import conn.ra.service.ReportService;
import conn.ra.service.VendorService;
import conn.ra.service.impl.ExcelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ReportController {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private ExcelImpl excelService;

    @GetMapping("/revenue-report")
    public String revenueReport(Model model
            , @RequestParam(name = "createdDateStart", required = false) String createdDateStart
            , @RequestParam(name = "createdDateEnd", required = false) String createdDateEnd) {
        List<RevenueReportDTO> revenues = reportService.revenueReport (createdDateStart, createdDateEnd );
        model.addAttribute ( "revenues", revenues );
        model.addAttribute ( "createdDateStart", createdDateStart );
        model.addAttribute ( "createdDateEnd", createdDateEnd );
        return "admin/revenue-report";
    }

    @GetMapping("/import-report")
    public String importReport(Model model
            , @RequestParam(name = "createdDateStart", required = false) String createdDateStart
            , @RequestParam(name = "createdDateEnd", required = false) String createdDateEnd
            , @RequestParam(name = "categoryId", required = false) String categoryId
            , @RequestParam(name = "vendorId", required = false) String vendorId) {
        List<Categories> categories = categoriesService.getByStatus ();
        model.addAttribute ( "categories", categories );
        List<Vendor> vendors = vendorService.getByStatus ();
        model.addAttribute ( "vendors", vendors );
        List<ImportReportDTO> importReport = reportService.importReport (createdDateStart, createdDateEnd,categoryId, vendorId );
        model.addAttribute ( "importReport", importReport );
        model.addAttribute ( "createdDateStart", createdDateStart );
        model.addAttribute ( "createdDateEnd", createdDateEnd );
        model.addAttribute ( "selectedCategoryId", categoryId );
        model.addAttribute ( "selectedVendorId", vendorId );
        return "admin/import-report";
    }

    @GetMapping("/sales-report")
    public String saleReport(Model model
            , @RequestParam(name = "createdDateStart", required = false) String createdDateStart
            , @RequestParam(name = "createdDateEnd", required = false) String createdDateEnd
            , @RequestParam(name = "categoryId", required = false) String categoryId) {
        List<Categories> categories = categoriesService.getByStatus ();
        model.addAttribute ( "categories", categories );
        List<SalesReportDTO> salesReport = reportService.salesReport (createdDateStart, createdDateEnd,categoryId );
        model.addAttribute ( "salesReport", salesReport );
        model.addAttribute ( "createdDateStart", createdDateStart );
        model.addAttribute ( "createdDateEnd", createdDateEnd );
        model.addAttribute ( "selectedCategoryId", categoryId );
        return "admin/sales-report";
    }

    @GetMapping("/exportExcelRevenue")
    public ResponseEntity<byte[]> exportExcelRevenue(
            @RequestParam(name = "createdDateStart", required = false) String createdDateStart
            , @RequestParam(name = "createdDateEnd", required = false) String createdDateEnd) {
        try {
            List<RevenueReportDTO> revenues = reportService.revenueReport (createdDateStart, createdDateEnd );
            ByteArrayInputStream in = excelService.exportToExcel ( revenues, "Thống kê doanh thu" );

            HttpHeaders headers = new HttpHeaders ();
            headers.setContentType ( MediaType.parseMediaType ( "application/vnd.ms-excel" ) );
            headers.setContentDispositionFormData ( "attachment", "data.xlsx" );
            headers.setCacheControl ( "must-revalidate, post-check=0, pre-check=0" );

            return new ResponseEntity<> ( in.readAllBytes (), headers, HttpStatus.OK );
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
    }

    @GetMapping("/exportExcelImport")
    public ResponseEntity<byte[]> exportExcelImport(
            @RequestParam(name = "createdDateStart", required = false) String createdDateStart
            , @RequestParam(name = "createdDateEnd", required = false) String createdDateEnd
            , @RequestParam(name = "categoryId", required = false) String categoryId
            , @RequestParam(name = "vendorId", required = false) String vendorId) {
        try {
            List<ImportReportDTO> importReport = reportService.importReport (createdDateStart, createdDateEnd, categoryId, vendorId );
            int id = 1;
            for (ImportReportDTO importReportDTO : importReport) {
                importReportDTO.setId ( (long) id++ );
            }
            ByteArrayInputStream in = excelService.exportToExcel ( importReport, "Thống kê nhập hàng" );

            HttpHeaders headers = new HttpHeaders ();
            headers.setContentType ( MediaType.parseMediaType ( "application/vnd.ms-excel" ) );
            headers.setContentDispositionFormData ( "attachment", "data.xlsx" );
            headers.setCacheControl ( "must-revalidate, post-check=0, pre-check=0" );

            return new ResponseEntity<> ( in.readAllBytes (), headers, HttpStatus.OK );
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
    }

    @GetMapping("/exportExcelSales")
    public ResponseEntity<byte[]> exportExcelSales(
            @RequestParam(name = "createdDateStart", required = false) String createdDateStart
            , @RequestParam(name = "createdDateEnd", required = false) String createdDateEnd
            , @RequestParam(name = "categoryId", required = false) String categoryId) {
        try {
            List<SalesReportDTO> salesReport = reportService.salesReport (createdDateStart, createdDateEnd, categoryId );
            int id = 1;
            for (SalesReportDTO salesReportDTO : salesReport) {
                salesReportDTO.setId ( (long) id++ );
            }
            ByteArrayInputStream in = excelService.exportToExcel ( salesReport, "Thống kê bán hàng" );
            HttpHeaders headers = new HttpHeaders ();
            headers.setContentType ( MediaType.parseMediaType ( "application/vnd.ms-excel" ) );
            headers.setContentDispositionFormData ( "attachment", "data.xlsx" );
            headers.setCacheControl ( "must-revalidate, post-check=0, pre-check=0" );

            return new ResponseEntity<> ( in.readAllBytes (), headers, HttpStatus.OK );
        } catch (IOException e) {
            throw new RuntimeException ( e );
        }
    }
}
