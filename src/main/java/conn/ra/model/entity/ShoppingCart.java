package conn.ra.model.entity;

import conn.ra.model.base.BaseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class ShoppingCart extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User users;

    @Column(name = "order_quantity")
    @Min(1)
    private Integer orderQuantity;
}
