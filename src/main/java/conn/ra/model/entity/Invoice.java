package conn.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import conn.ra.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Invoice extends BaseModel {
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "created_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date created;

    @Column(name = "total_price")
    private Double totalPrice;

    private String note;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor vendor;

    @OneToMany(mappedBy = "invoice")
    @JsonIgnore
    List<InvoiceDetail> invoiceDetails;
}
