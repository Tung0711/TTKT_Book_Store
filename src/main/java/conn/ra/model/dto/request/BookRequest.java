package conn.ra.model.dto.request;

import conn.ra.model.entity.Categories;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    @NotBlank(message = "Tên sách không được để trống!")
    private String bookName;
    @NotBlank(message = "Mô tả không được để trống!")
    private String description;
    @NotBlank(message = "Tên tác giả không được để trống")
    private String author;
    @NotNull(message = "Đơn giá không được để trống!")
    private Double unitPrice;
    @NotNull(message = "Số lượng trong kho không được để trống")
    private Integer stockQuantity;
    private String image;
    @NotNull(message = "Danh mục không được để trống")
    private Categories categories;
}
