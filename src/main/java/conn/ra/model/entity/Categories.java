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
public class Categories extends BaseModel {
    @Column(name = "catalog_name", unique = true)
    private String catalogName;

    private String description; 

    private Boolean status;

    @OneToMany(mappedBy = "categories")
    @JsonIgnore
    List<Book> books;
}
