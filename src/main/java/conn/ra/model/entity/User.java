package conn.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import conn.ra.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User extends BaseModel {
    @Column(unique = true)
    private String username;

    private String fullName;

    private String avatar;

    private String email;

    private Date dateOfBirth;

    private Boolean status;

    private String password;

    @Column(unique = true)
    private String phone;

    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<> ();

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<Orders> orders;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<ShoppingCart> shoppingCarts;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<WishList> wishLists;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<Address> addresses;
}
