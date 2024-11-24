package conn.ra.model.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShoppingCartRequest {
    private Long bookId;
    private int orderQuantity;
}
