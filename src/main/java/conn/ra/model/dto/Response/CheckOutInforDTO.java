package conn.ra.model.dto.Response;

import conn.ra.model.enums.PaymentMethods;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CheckOutInforDTO {
    private String receiveName;
    private String receivePhone;
    private String receiveAddress;
    private String note;
    private PaymentMethods paymentMethods;
}
