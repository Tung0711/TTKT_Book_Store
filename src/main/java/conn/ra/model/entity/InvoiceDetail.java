package conn.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "invoice_detail")
@IdClass(InvoiceDetailId.class)
public class InvoiceDetail{
    @Id
    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private Invoice invoice;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book books;

    @Column(name = "invoice_price")
    private Double price;

    @Column(name = "order_quantity")
    @Min(1)
    private Integer orderQuantity;

    @Column(name = "created_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date create;
}
