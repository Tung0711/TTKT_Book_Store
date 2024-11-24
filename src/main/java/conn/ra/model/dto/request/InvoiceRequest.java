package conn.ra.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequest {
    private Long id;
    @NotNull(message = "Nhà cung cấp không được để trống")
    private Long vendorId;
    private String note;
}
