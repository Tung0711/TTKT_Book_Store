package conn.ra.model.entity;

import conn.ra.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WishList extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
}

