package conn.ra.model.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressRequest {
    private String address;
    private String phone;
    private String receiveName;
}
