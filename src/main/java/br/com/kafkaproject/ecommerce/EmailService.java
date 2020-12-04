package br.com.kafkaproject.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {

    private static final String TOPIC_NAME = "ECOMMERCE_SEND_EMAIL";

    public static void main(String[] args) {
        var emailService = new EmailService();
        try (var service = new KafkaService(TOPIC_NAME, emailService::parse, EmailService.class.getSimpleName())) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("================================================================");
        System.out.println("Send email");
        System.out.println(record.key() + "key");
        System.out.println(record.value() + "value");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Email sent");
    }
}
