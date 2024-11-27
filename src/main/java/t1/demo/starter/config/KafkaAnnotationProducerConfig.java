package t1.demo.starter.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import t1.demo.starter.dto.MetricsDTO;
import t1.demo.starter.kafka.KafkaErrorLogProducer;
import t1.demo.starter.kafka.KafkaMetricsProducer;
import t1.demo.starter.model.DataSourceErrorLog;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(prefix = "annotation.logging", name = "enabled", havingValue = "true", matchIfMissing = true)
public class KafkaAnnotationProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, DataSourceErrorLog> errorLogProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean(name = "errorLogKafkaTemplate")
    public KafkaTemplate<String, DataSourceErrorLog> errorLogKafkaTemplate() {
        return new KafkaTemplate<>(errorLogProducerFactory());
    }

    @Bean
    public ProducerFactory<String, MetricsDTO> metricsProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean(name = "metricsKafkaTemplate")
    public KafkaTemplate<String, MetricsDTO> metricsKafkaTemplate() {
        return new KafkaTemplate<>(metricsProducerFactory());
    }

    @Bean
    public KafkaErrorLogProducer kafkaErrorLogProducer(@Qualifier("errorLogKafkaTemplate") KafkaTemplate<String, DataSourceErrorLog> template) {
        return new KafkaErrorLogProducer(template);
    }

    @Bean
    public KafkaMetricsProducer kafkaMetricsProducer(@Qualifier("metricsKafkaTemplate")KafkaTemplate<String, MetricsDTO> template) {
        return new KafkaMetricsProducer(template);
    }
}
