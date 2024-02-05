package com.honeybee.magok.repository;

import com.honeybee.magok.domain.PriceBio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BioRepository extends JpaRepository<PriceBio, Long> {

    @Query("select p from PriceBio p" +
            " where year(p.date)  = :year order by p.date")
    List<PriceBio> findByYear(@Param("year") int year);


    @Query("SELECT p,p2 FROM PriceBio p " +
            "INNER JOIN PriceBio p2 ON p2.date = FUNCTION('DATE_ADD',p.date, 1) " +
            "WHERE FUNCTION('YEAR',p.date) =:year-3 ")
    List<Object[]> findAllByYear(@Param("year") int year);
}


