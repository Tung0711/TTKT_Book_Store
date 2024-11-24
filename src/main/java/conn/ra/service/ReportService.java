package conn.ra.service;

import conn.ra.model.dto.Response.ImportReportDTO;
import conn.ra.model.dto.Response.RevenueReportDTO;
import conn.ra.model.dto.Response.SalesReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ReportService {

    List<RevenueReportDTO> revenueReport(String createdDateStart, String createdDateEnd);

    List<ImportReportDTO> importReport(String createdDateStart, String createdDateEnd, String categoryId, String vendorId);

    List<SalesReportDTO> salesReport(String createdDateStart, String createdDateEnd, String categoryId);
}
