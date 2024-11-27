package t1.demo.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import t1.demo.starter.aop.LogDataSourceErrorAspect;
import t1.demo.starter.aop.MetricsAspect;
import t1.demo.starter.kafka.KafkaErrorLogProducer;
import t1.demo.starter.kafka.KafkaMetricsProducer;
import t1.demo.starter.model.DataSourceErrorLog;
import t1.demo.starter.repository.DataSourceErrorLogRepository;
import t1.demo.starter.service.ErrorLogService;

@Configuration
@ConditionalOnProperty(prefix = "annotation.logging", name = "enabled", havingValue = "true", matchIfMissing = true)
@EntityScan(basePackages = "t1.demo.starter.model")
@EnableJpaRepositories(basePackages = "t1.demo.starter.repository")
public class DemoStarterConfig {

    @Bean
    public LogDataSourceErrorAspect logDataSourceErrorAspect(ErrorLogService errorLogService, KafkaErrorLogProducer kafkaErrorLogProducer) {
        return new LogDataSourceErrorAspect(errorLogService, kafkaErrorLogProducer);
    }

    @Bean
    public MetricsAspect metricsAspect(KafkaMetricsProducer kafkaMetricsProducer) {
        return new MetricsAspect(kafkaMetricsProducer);
    }

    @Bean
    public ErrorLogService errorLogService(DataSourceErrorLogRepository errorLogRepository) {
        return new ErrorLogService (errorLogRepository);
    }

    
}
