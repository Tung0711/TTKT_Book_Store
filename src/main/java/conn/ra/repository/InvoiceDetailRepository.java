package conn.ra.repository;

import conn.ra.model.entity.Book;
import conn.ra.model.entity.Invoice;
import conn.ra.model.entity.InvoiceDetail;
import conn.ra.model.entity.InvoiceDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, InvoiceDetailId> {
    @Query("select iv from InvoiceDetail iv where iv.invoice.id = :invoiceId")
    List<InvoiceDetail> findByInvoiceId(Long invoiceId);

    boolean existsByBooksAndInvoice(Book books, Invoice invoice);
}
