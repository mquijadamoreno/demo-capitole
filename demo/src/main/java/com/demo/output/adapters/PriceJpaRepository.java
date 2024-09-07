package com.demo.output.adapters;

import com.demo.output.pojo.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId " +
            "AND :date BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC")
    Optional<PriceEntity> findTopPrice(Integer productId, Integer brandId, LocalDateTime date);
}
