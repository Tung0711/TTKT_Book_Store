package conn.ra.model.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InvoiceDetailId implements Serializable {
    private Invoice invoice;
    private Book books;
}
