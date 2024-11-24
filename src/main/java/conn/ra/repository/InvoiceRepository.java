package conn.ra.repository;

import conn.ra.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("select i from Invoice i where i.vendor.id = :vendorId")
    List<Invoice> findAllByVendorId(Long vendorId);

    @Query("select i from Invoice i where i.vendor.id = :vendorId and i.serialNumber = :serial")
    Invoice findByVendorIdAndSerial(Long vendorId, String serial);
}
