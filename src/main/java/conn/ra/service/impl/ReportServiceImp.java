package conn.ra.service.impl;

import conn.ra.model.dto.Response.ImportReportDTO;
import conn.ra.model.dto.Response.RevenueReportDTO;
import conn.ra.model.dto.Response.SalesReportDTO;
import conn.ra.repository.ImportReportRepository;
import conn.ra.repository.RevenueReportRepository;
import conn.ra.repository.SaleReportRepository;
import conn.ra.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImp implements ReportService {

    private final RevenueReportRepository revenueReportRepository;

    private final ImportReportRepository importReportRepository;

    private final SaleReportRepository saleReportRepository;

    @Override
    public List<RevenueReportDTO> revenueReport(String createdDateStart, String createdDateEnd) {
        return revenueReportRepository.revenue (createdDateStart, createdDateEnd );
    }

    @Override
    public List<ImportReportDTO> importReport(String createdDateStart, String createdDateEnd, String categoryId, String vendorId) {
        return importReportRepository.importReport (createdDateStart, createdDateEnd, categoryId, vendorId );
    }

    @Override
    public List<SalesReportDTO> salesReport(String createdDateStart, String createdDateEnd, String categoryId) {
        return saleReportRepository.salesReport (createdDateStart, createdDateEnd, categoryId );
    }


}
