package conn.ra.service.impl;

import conn.ra.model.dto.request.InvoiceRequest;
import conn.ra.model.entity.Book;
import conn.ra.model.entity.Invoice;
import conn.ra.model.entity.InvoiceDetail;
import conn.ra.repository.InvoiceRepository;
import conn.ra.service.BookService;
import conn.ra.service.InvoiceService;
import conn.ra.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private BookService bookService;

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll ();
    }

    @Override
    public List<Invoice> getAll(Long vendorId) {
        return invoiceRepository.findAllByVendorId ( vendorId );
    }

    @Override
    public void addInvoice(InvoiceRequest invoiceRequest) {
        Invoice invoice = entityMapRequest ( invoiceRequest );
        invoice.setSerialNumber ( UUID.randomUUID ().toString () );
        invoice.setTotalPrice ( (double) 0 );
        invoice.setCreated ( new java.sql.Date ( new java.util.Date ().getTime () ) );
        invoice.setStatus ( false );
        save ( invoice );
    }

    @Override
    public Invoice entityMapRequest(InvoiceRequest invoiceRequest) {
        return Invoice.builder ()
                .vendor ( vendorService.findById ( invoiceRequest.getVendorId () ) )
                .note ( invoiceRequest.getNote () )
                .build ();
    }

    @Override
    public Invoice getBySerial(Long vendorId, String serial) {
        return invoiceRepository.findByVendorIdAndSerial ( vendorId, serial );
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save ( invoice );
    }

    @Override
    public Invoice findById(Long id) {
        return invoiceRepository.findById ( id ).orElse ( null );
    }

    @Override
    public Boolean invoiceConfirm(Long id) {
        Invoice invoice = findById ( id );
        invoice.setStatus ( true );
        List<InvoiceDetail> invoiceDetails = invoice.getInvoiceDetails ();
        if (!invoiceDetails.isEmpty ()) {
            for (InvoiceDetail invoiceDetail : invoiceDetails) {
                Book books = invoiceDetail.getBooks ();
                books.setStockQuantity ( books.getStockQuantity () + invoiceDetail.getOrderQuantity () );
                bookService.edit ( books );
            }
            save ( invoice );
            return true;
        }
        return false;
    }
}
