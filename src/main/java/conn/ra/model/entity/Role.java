package conn.ra.model.entity;

import conn.ra.model.base.BaseModel;
import conn.ra.model.enums.ERole;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Role extends BaseModel {
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private ERole roleName;
}
