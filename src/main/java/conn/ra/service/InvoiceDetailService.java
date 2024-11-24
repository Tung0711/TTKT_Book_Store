package conn.ra.service;

import conn.ra.model.dto.request.InvoiceDetailRequest;
import conn.ra.model.entity.InvoiceDetail;

import java.util.List;

public interface InvoiceDetailService {
    List<InvoiceDetail> getByInvoiceId(Long invoiceId);

    void addInvoiceDetail(InvoiceDetailRequest invoiceDetailRequest, Long invoiceId);

    Double totalPrice(Long invoiceId);

    InvoiceDetail entityMapRequest(InvoiceDetailRequest invoiceDetailRequest);

    InvoiceDetail save(InvoiceDetail invoiceDetail);

}
