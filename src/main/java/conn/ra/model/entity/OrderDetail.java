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
@Table(name = "order_detail")
@IdClass(OrderDetailId.class)
public class OrderDetail{
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book books;

    @Column(name = "unit_price")
    private Double price;

    @Column(name = "order_quantity")
    @Min(1)
    private Integer orderQuantity;

    @Column(name = "created_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date create;
}
