package conn.ra.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailRequest {
    private Long id;
    private Long invoiceId;
    private Long bookId;
    @NotNull(message = "Số lượng không được để trống!")
    private Integer orderQuantity;
    @NotNull(message = "Đơn giá nhập hàng không được để trống!")
    private Double unitPrice;
}
