package com.bayzdelivery.repositories;

import com.bayzdelivery.model.Delivery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.Instant;
import java.util.List;

@RestResource(exported = false)
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

    @Query(value = "SELECT * FROM delivery d " +
            "WHERE d.delivery_man_id = :deliveryManId " +
            "AND ((d.start_time <= :endTime AND (d.end_time IS NULL OR d.end_time >= :startTime)) " +
            "OR (d.start_time >= :startTime AND d.start_time <= :endTime)) " ,
            nativeQuery = true)
    List<Delivery> findOverlappingDeliveriesForDeliveryMan(
            @Param("deliveryManId") Long deliveryManId,
            @Param("startTime") Instant startTime,
            @Param("endTime") Instant endTime
    );

    @Query(value =
            "SELECT d.delivery_man_id as id, p.name, p.email, SUM(d.comission) as total_commission, AVG(d.comission) as average_commission " +
                    "FROM delivery d " +
                    "JOIN person p ON d.delivery_man_id = p.id " +
                    "WHERE d.start_time >= :startTime AND d.start_time <= :endTime " +
                    "GROUP BY d.delivery_man_id, p.name, p.email " +
                    "ORDER BY total_commission DESC " +
                    "LIMIT 3",
            nativeQuery = true)
    List<Object[]> findTopDeliveryMenByCommission(
            @Param("startTime") Instant startTime,
            @Param("endTime") Instant endTime
    );

    @Query("SELECT d FROM Delivery d WHERE d.startTime < :thresholdTime AND d.endTime IS NULL")
    List<Delivery> findDelayedDeliveries(@Param("thresholdTime") Instant thresholdTime);
}
