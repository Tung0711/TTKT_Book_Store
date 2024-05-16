package conn.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import conn.ra.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Book extends BaseModel {
    @Column(name = "book_images")
    private String images;
    @Column(name = "book_name", unique = true)
    private String bookName;
    @Column(name = "book_author")
    private String author;
    private Double price;
    @Column(name = "book_description")
    private String description;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "catalog_id", referencedColumnName = "id")
    private Categories categories;
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    List<ShoppingCart> shoppingCarts;
}
