package conn.ra.model.entity;

import conn.ra.model.base.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Address extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User users;

    @Column(name = "receive_address")
    private String address;

    @Column(length = 15,name = "receive_phone")
    private String phone;

    @Column(name = "receive_name")
    private String receiveName;
}
