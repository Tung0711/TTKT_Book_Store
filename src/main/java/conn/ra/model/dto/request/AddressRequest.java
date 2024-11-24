package conn.ra.model.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressRequest {
    private String receiveAddress;
    private String receivePhone;
    private String receiveName;
}
