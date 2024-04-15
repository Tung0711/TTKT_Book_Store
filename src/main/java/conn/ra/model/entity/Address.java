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
public class Address extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User users;

    @Column(name = "full_address")
    private String address;
    @Column(length = 15)
    private String phone;

    @Column(name = "receive_name")
    private String receiveName;
}