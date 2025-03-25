package com.bayzdelivery.jobs;

import com.bayzdelivery.exceptions.GlobalExceptionHandler;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.repositories.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
public class DelayedDeliveryNotifier {

    private static final Logger LOG = LoggerFactory.getLogger(DelayedDeliveryNotifier.class);

    private static final Duration DELAY_THRESHOLD = Duration.ofMinutes(45);

    @Autowired
    DeliveryRepository deliveryRepository;

    /**
     *  Use this method for the TASK 3
     */
    @Scheduled(fixedDelay = 30000)
    public void checkDelayedDeliveries() {

        LOG.info("Checking for delayed deliveries...");

        Instant now = Instant.now();

        Instant thresholdTime = now.minus(DELAY_THRESHOLD);

        List<Delivery> delayedDeliveries = deliveryRepository.findDelayedDeliveries(thresholdTime);
        for (Delivery delivery : delayedDeliveries) {
            Duration delayDuration = Duration.between(delivery.getStartTime(), now);

            LOG.info("Found delayed delivery - ID: {}, Started: {}, Delay: {} minutes",
                    delivery.getId(),
                    delivery.getStartTime(),
                    delayDuration.toMinutes());

            notifyCustomerSupport();
        }

    }


    /**
     * This method should be called to notify customer support team
     * It just writes notification on console but it may be email or push notification in real.
     * So that this method should run in an async way.
     */
    @Async
    public void notifyCustomerSupport() {
        LOG.info("Customer support team is notified!");
    }
}
