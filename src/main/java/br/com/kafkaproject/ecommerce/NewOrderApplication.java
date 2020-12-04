package br.com.kafkaproject.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderApplication {

    private static final String TOPIC_NEW_ORDER = "ECOMMERCE_NEW_ORDER";
    private static final String TOPIC_SEND_EMAIL = "ECOMMERCE_SEND_EMAIL";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(var dispatcherService = new KafkaDispatcher<Order>()) {

            for (int i = 0; i < 10; i++) {
                var userId = UUID.randomUUID().toString();
                var orderId = UUID.randomUUID().toString();
                var amount = BigDecimal.valueOf(Math.random() * 5000 + 1);
                Order order = new Order(userId, orderId, amount);

                var email = "Thanks for you order! We are processing your order!";

                dispatcherService.send(TOPIC_NEW_ORDER, userId, order);
//                dispatcherService.send(TOPIC_SEND_EMAIL, userId, email);
            }
        }
    }

}
