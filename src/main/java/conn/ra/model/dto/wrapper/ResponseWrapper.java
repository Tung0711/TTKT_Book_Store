package conn.ra.model.dto.wrapper;

import conn.ra.model.enums.EHttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseWrapper<T>{
    private EHttpStatus httpStatus;
    private Integer httpStatusCode;
    private String httpStatusName;
    private T content;
}
