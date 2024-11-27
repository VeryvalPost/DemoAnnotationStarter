package t1.demo.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataSourceErrorLogDTO {

    private Long id;
    private String stackTraceText;
    private String stackTraceMessage;
    private String methodSignature;

}