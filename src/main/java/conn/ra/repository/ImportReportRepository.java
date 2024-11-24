package conn.ra.repository;

import conn.ra.model.dto.Response.ImportReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportReportRepository extends JpaRepository<ImportReportDTO, Long> {
    @Query(value = "SELECT b.id AS id, b.book_name AS book_name,  iv.vendor_name AS vendor_name,\n" +
            "       c.catalog_name AS catalog_name,\n" +
            "       COALESCE(SUM(iv.quantity), 0) AS input_quantity, COALESCE(SUM(iv.amount), 0) AS total_amount,\n" +
            "       COALESCE(MAX(iv.created_date),  '1970-01-01') AS created_date\n" +
            "FROM book b LEFT JOIN categories c ON b.catalog_id = c.id\n" +
            "    JOIN ( SELECT iv.book_id, SUM(iv.order_quantity) AS quantity, SUM(iv.order_quantity*iv.invoice_price) as amount,\n" +
            "iv.created_date, v.vendor_name, v.id as vendor_id\n" +
            "FROM invoice_detail iv JOIN invoice i ON iv.invoice_id = i.id JOIN vendor v on v.id = i.vendor_id\n" +
            "WHERE iv.created_date BETWEEN COALESCE(:createdDateStart, iv.created_date) AND COALESCE(:createdDateEnd,  iv.created_date)\n" +
            "AND i.status = true\n" +
            "GROUP BY  iv.book_id,  iv.created_date, v.id) iv ON b.id =  iv.book_id\n" +
            "WHERE (b.catalog_id = COALESCE(:catalog_id, b.catalog_id))\n" +
            "AND ( iv.vendor_id = COALESCE(:vendorId,  iv.vendor_id))\n" +
            "GROUP BY b.id, b.book_name, c.catalog_name, iv.vendor_id\n" +
            "ORDER BY created_date;", nativeQuery = true)
    List<ImportReportDTO> importReport(String createdDateStart, String createdDateEnd, String catalog_id, String vendorId);
}
