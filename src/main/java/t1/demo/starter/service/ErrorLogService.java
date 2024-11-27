package t1.demo.starter.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import t1.demo.starter.model.DataSourceErrorLog;
import t1.demo.starter.repository.DataSourceErrorLogRepository;


@Service
public class ErrorLogService {
    private final DataSourceErrorLogRepository errorLogRepository;

    public ErrorLogService(DataSourceErrorLogRepository errorLogRepository) {
        this.errorLogRepository = errorLogRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveErrorLog(DataSourceErrorLog errorLog) {
        errorLogRepository.save(errorLog);
    }
}