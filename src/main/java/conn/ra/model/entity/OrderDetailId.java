package conn.ra.model.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailId implements Serializable {
    private Orders orders;
    private Book books;
}
