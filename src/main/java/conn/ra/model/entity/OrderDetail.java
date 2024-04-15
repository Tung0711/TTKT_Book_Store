package conn.ra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "order_detail")
@IdClass(OrderDetailId.class)
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @Column(length = 100)
    private String name;

    @Column(name = "unit_price")
    private Double price;

    @Column(name = "order_quantity")
    @Min(1)
    private Integer orderQuantity;
}
