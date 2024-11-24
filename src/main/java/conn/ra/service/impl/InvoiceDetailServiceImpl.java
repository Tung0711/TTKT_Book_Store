package conn.ra.service.impl;

import conn.ra.model.dto.request.InvoiceDetailRequest;
import conn.ra.model.entity.Invoice;
import conn.ra.model.entity.InvoiceDetail;
import conn.ra.repository.InvoiceDetailRepository;
import conn.ra.service.BookService;
import conn.ra.service.InvoiceDetailService;
import conn.ra.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private BookService bookService;

    @Override
    public List<InvoiceDetail> getByInvoiceId(Long invoiceId) {
        return invoiceDetailRepository.findByInvoiceId ( invoiceId );
    }

    @Override
    public InvoiceDetail save(InvoiceDetail invoiceDetail) {
        return invoiceDetailRepository.save ( invoiceDetail );
    }

    @Override
    public void addInvoiceDetail(InvoiceDetailRequest invoiceDetailRequest, Long invoiceId) {
        InvoiceDetail invoiceDetail = entityMapRequest ( invoiceDetailRequest );
        invoiceDetail.setInvoice ( invoiceService.findById ( invoiceId ) );
        save ( invoiceDetail );
    }

    @Override
    public Double totalPrice(Long invoiceId) {
        Invoice invoice = invoiceService.findById ( invoiceId );
        double totalPrice = 0;
        for (InvoiceDetail invoiceDetail : invoice.getInvoiceDetails ()) {
            totalPrice += invoiceDetail.getPrice () * invoiceDetail.getOrderQuantity ();
        }
        invoice.setTotalPrice ( totalPrice );
        invoiceService.save ( invoice );
        return totalPrice;
    }

    @Override
    public InvoiceDetail entityMapRequest(InvoiceDetailRequest invoiceDetailRequest) {
        return InvoiceDetail.builder ()
                .books ( bookService.findById ( invoiceDetailRequest.getBookId () ) )
                .orderQuantity ( invoiceDetailRequest.getOrderQuantity () )
                .price ( invoiceDetailRequest.getUnitPrice () )
                .create ( new java.sql.Date ( new java.util.Date ().getTime () ) )
                .build ();
    }
}
