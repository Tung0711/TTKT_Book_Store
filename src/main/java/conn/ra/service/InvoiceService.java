package conn.ra.service;

import conn.ra.model.dto.request.InvoiceRequest;
import conn.ra.model.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvoiceService {
    List<Invoice> findAll();

    List<Invoice> getAll(Long vendorId);

    void addInvoice(InvoiceRequest invoiceRequest);

    Invoice entityMapRequest(InvoiceRequest invoiceRequest);

    Invoice getBySerial(Long vendorId, String serial);

    Invoice save(Invoice invoice);

    Invoice findById(Long id);

    Boolean invoiceConfirm(Long id);
}
