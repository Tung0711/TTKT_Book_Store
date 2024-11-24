package conn.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import conn.ra.model.base.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Vendor extends BaseModel {
    @Column(name = "vendor_name", unique = true)
    private String vendorName;

    private String phone;

    private String address;

    private Boolean status;

    @OneToMany(mappedBy = "vendor")
    @JsonIgnore
    List<Invoice> invoice;
}
