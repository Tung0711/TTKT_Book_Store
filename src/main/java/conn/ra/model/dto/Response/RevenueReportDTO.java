package conn.ra.model.dto.Response;

import conn.ra.service.ExcelExportable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RevenueReportDTO implements ExcelExportable {
    @Id
    private Long id;
    private LocalDate createdDate;
    private Double importAmount;
    private Double saleAmount;
    private Double totalAmount;

    @Override
    public String[] getHeaders() {
        return new String[]{"STT", "Ngày/tháng/năm", "Số tiền nhập hàng", "Số tiền bán hàng", "Số tiền lỗ/lãi"};
    }

    @Override
    public String[] getData() {
        return new String[]{id.toString (), createdDate.format ( DateTimeFormatter.ofPattern ( "dd/MM/yyyy" ) ),
                NumberFormat.getInstance ( new Locale ( "vi", "VN" ) ).format ( importAmount ) + " VND",
                NumberFormat.getInstance ( new Locale ( "vi", "VN" ) ).format ( saleAmount ) + " VND",
                NumberFormat.getInstance ( new Locale ( "vi", "VN" ) ).format ( totalAmount ) + " VND"
        };
    }
}
