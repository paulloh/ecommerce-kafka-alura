package br.com.kafkaproject.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {

    private static final String TOPIC_NAME = "ECOMMERCE_NEW_ORDER";

    public static void main(String[] args) {
        var fraudeDetectorService = new FraudDetectorService();
        try (var kafkaService = new KafkaService(TOPIC_NAME,
                fraudeDetectorService::parse,
                FraudDetectorService.class.getSimpleName())) {
            kafkaService.run();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("================================================================");
        System.out.println("Processing new order, checking for fraud");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println("Partition: " + record.partition());
        System.out.println("Offset: " + record.offset());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Order processed");
    }
}
