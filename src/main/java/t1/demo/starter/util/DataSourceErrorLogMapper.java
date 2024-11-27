package t1.demo.starter.util;

import t1.demo.starter.dto.DataSourceErrorLogDTO;
import t1.demo.starter.model.DataSourceErrorLog;

public class DataSourceErrorLogMapper {


    public static DataSourceErrorLogDTO toDTO(DataSourceErrorLog entity) {
        return new DataSourceErrorLogDTO(
                entity.getId(),
                entity.getStackTraceText(),
                entity.getStackTraceMessage(),
                entity.getMethodSignature()
        );
    }


    public static DataSourceErrorLog toEntity(DataSourceErrorLogDTO dto) {
        return new DataSourceErrorLog(
                dto.getId(),
                dto.getStackTraceText(),
                dto.getStackTraceMessage(),
                dto.getMethodSignature()
        );
    }
}