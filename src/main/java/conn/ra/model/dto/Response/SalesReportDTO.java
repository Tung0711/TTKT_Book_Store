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
public class SalesReportDTO implements ExcelExportable {
    @Id
    private Long id;
    private String bookName;
    private String categoryName;
    private Integer outputQuantity;
    private Double totalAmount;
    private LocalDate createdDate;

    @Override
    public String[] getHeaders() {
        return new String[]{"STT", "Tên sản phẩm", "Tên danh mục", "Số lượng bán hàng", "Tổng tiền", "Ngày/tháng/năm"};
    }

    @Override
    public String[] getData() {
        return new String[]{id.toString (), bookName, categoryName, outputQuantity.toString ()
                , NumberFormat.getInstance ( new Locale ( "vi", "VN" ) ).format ( totalAmount ) + " VND"
                , createdDate.format ( DateTimeFormatter.ofPattern ( "dd/MM/yyyy" ) )};
    }
}
