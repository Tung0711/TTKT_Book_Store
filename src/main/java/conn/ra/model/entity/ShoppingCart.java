package conn.ra.model.entity;

import conn.ra.model.base.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Book books;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User users;

    @Column(name = "order_quantity")
    @Min(1)
    private Integer orderQuantity;
}
