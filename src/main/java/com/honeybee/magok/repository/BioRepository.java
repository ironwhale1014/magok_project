package com.honeybee.magok.repository;

import com.honeybee.magok.domain.PriceBio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BioRepository extends JpaRepository<PriceBio, Long> {

    @Query("select p from PriceBio p where year(p.date)  = :year order by p.date")
    List<PriceBio> findByYear(@Param("year") int year);
}