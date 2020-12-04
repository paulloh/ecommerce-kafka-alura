package br.com.kafkaproject.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Properties;
import java.util.regex.Pattern;

public class LogService {

    public static void main(String[] args) throws InterruptedException {
        var consumer = new KafkaConsumer<String, String>(properties());
        while (true){
            consumer.subscribe(Pattern.compile("ECOMMERCE.*"));
            var records = consumer.poll(Duration.ofMillis(100));
            if (!records.isEmpty()){
                System.out.println(records.count() + " Emails enviados");
                for (var record : records){
                    System.out.println("================================================================");
                    System.out.println("LOG");
                    System.out.println(record.key());
                    System.out.println(record.value());
                    Thread.sleep(1000);
                }
            }
        }

    }

    private static Properties properties() {
        var properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, LogService.class.getSimpleName());

        return properties;
    }
}
