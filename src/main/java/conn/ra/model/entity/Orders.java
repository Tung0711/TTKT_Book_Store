package conn.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import conn.ra.model.base.BaseModel;
import conn.ra.model.enums.EOrder;
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
public class Orders extends BaseModel {
    @Column(name = "serial_number")
    private String orderNumber;

    @Column(name = "total_price")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private EOrder status;
    private String note;

    @Column(name = "created_at")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date created;

    @Column(name = "receive_name")
    private String receiveName;

    @Column(name = "receive_address")
    private String receiveAddress;

    @Column(name = "receive_phone")
    private String receivePhone;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User users;

    @OneToMany(mappedBy = "orders")
    @JsonIgnore
    List<OrderDetail> orderDetails;
}
