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
}
