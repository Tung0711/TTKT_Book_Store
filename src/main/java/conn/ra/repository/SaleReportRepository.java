package conn.ra.repository;

import conn.ra.model.dto.Response.SalesReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleReportRepository extends JpaRepository<SalesReportDTO, Long> {
    @Query(value = "SELECT p.id AS id, p.book_name AS book_name, c.catalog_name AS category_name,\n" +
            "       COALESCE(SUM(od.quantity), 0) AS output_quantity,\n" +
            "       COALESCE(SUM(od.amount), 0) AS total_amount,\n" +
            "       COALESCE(MAX(od.created_date),  '1970-01-01') AS created_date\n" +
            "FROM book p LEFT JOIN categories c ON p.catalog_id = c.id JOIN\n" +
            "    ( SELECT od.book_id, SUM(od.order_quantity) AS quantity,\n" +
            "             SUM(od.order_quantity*p.price) as amount, od.created_date\n" +
            "      FROM order_detail od JOIN orders o ON od.order_id = o.id JOIN book p ON od.book_id = p.id\n" +
            "      WHERE od.created_date BETWEEN COALESCE(:createdDateStart, od.created_date) AND COALESCE(:createdDateEnd, od.created_date)\n" +
            "        AND o.status = 'SUCCESS'\n" +
            "      GROUP BY od.book_id, od.created_date) od ON p.id = od.book_id\n" +
            "WHERE p.catalog_id = COALESCE(:categoryId, p.catalog_id)\n" +
            "GROUP BY p.id, p.book_name, c.catalog_name\n" +
            "ORDER BY created_date;",nativeQuery = true)
    List<SalesReportDTO> salesReport(String createdDateStart, String createdDateEnd, String categoryId);
}
