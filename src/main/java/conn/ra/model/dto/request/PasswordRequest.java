package conn.ra.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequest {
    @NotEmpty(message = "Mật khẩu cũ không được để trống!")
    private String oldPassword;
    @NotEmpty(message = "Mật khẩu mới không được để trống!")
    private String newPassword;
    @NotEmpty(message = "Mật khẩu xác nhận không được để trống!")
    private String confirmPassword;
}
