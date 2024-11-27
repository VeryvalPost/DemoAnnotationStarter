package t1.demo.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetricsDTO {
    private String methodName;
    private String parameters;
    private long workingTime;
}