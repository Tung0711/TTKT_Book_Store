package conn.ra.repository;

import conn.ra.model.dto.Response.RevenueReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevenueReportRepository extends JpaRepository<RevenueReportDTO, Long> {
    @Query(value = "SELECT ROW_NUMBER() OVER(ORDER BY createdDate) as id, createdDate as created_date,\n" +
            "       SUM(importAmount) AS import_amount, SUM(saleAmount) AS sale_amount,\n" +
            "       (SUM(saleAmount) - SUM(importAmount)) AS total_amount\n" +
            "FROM ( SELECT i.created_date AS createdDate, SUM(i.total_price) AS importAmount, 0 AS saleAmount\n" +
            "       FROM invoice i WHERE i.created_date BETWEEN COALESCE(:createdDateStart, i.created_date) AND COALESCE(:createdDateEnd, i.created_date)\n" +
            "                      GROUP BY i.created_date\n" +
            "UNION ALL SELECT o.created_at AS createdDate, 0 AS importAmount, SUM(o.total_price) AS saleAmount\n" +
            "    FROM orders o WHERE o.created_at BETWEEN COALESCE(:createdDateStart, o.created_at) AND COALESCE(:createdDateEnd, o.created_at)\n" +
            "                     AND o.status = 'SUCCESS'\n" +
            "GROUP BY o.created_at) AS combined\n" +
            "GROUP BY createdDate ORDER BY createdDate;",
            nativeQuery = true)
    List<RevenueReportDTO> revenue(String createdDateStart, String createdDateEnd);


}
