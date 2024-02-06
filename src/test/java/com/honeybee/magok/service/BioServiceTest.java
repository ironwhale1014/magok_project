package com.honeybee.magok.service;

import com.honeybee.magok.domain.PriceBio;
import com.honeybee.magok.dto.AddPriceBioRequest;
import com.honeybee.magok.dto.UpdatePriceBioRequest;
import com.honeybee.magok.repository.BioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BioServiceTest {


    private final BioService bioService;

    private final BioRepository repository;


    @Autowired
    BioServiceTest(BioService bioService, BioRepository repository) {
        this.bioService = bioService;
        this.repository = repository;
    }
//    @Test
//    void save() {
//        final String date = "2023-12-01";
//        final Integer heat = 803;
//        final Integer unit = 32925;
//        final Integer etc = 0;
//        final String memo = "test";
//        AddPriceBioRequest request = new AddPriceBioRequest(date, heat, unit, etc, memo);
//
//        PriceBio saved = bioService.save(request);
//
//
//        PriceBio findBio = bioService.findById(saved.getId());
//
//
//        assertThat(findBio.getHeat()).isEqualTo(heat);
//
//    }
//    @Test
//    void delete(){
//        bioService.delete(62L);
//    }

    @Test
    void update() {


        final String date = "2023-12-01";
        final Integer heat1 = 803;
        final Integer unit = 32925;
        final Integer etc = 0;
        final String memo1 = "test";
        AddPriceBioRequest request1 = new AddPriceBioRequest(date, heat1, unit, etc, memo1);

        PriceBio byId = bioService.save(request1);


        final Integer heat = 1231231;
        final String memo = "update123";

        UpdatePriceBioRequest request =
                UpdatePriceBioRequest.builder()
                        .date("2023-12-01").useAmount(heat).unit(byId.getUnit()).etcPrice(byId.getEtcPrice())
                        .supplyPrice(byId.getSupplyPrice()).vat(byId.getVat()).totalPrice(100000).memo(memo).build();

        PriceBio updated = bioService.update(byId.getId(), request);

        System.out.println(updated.getUseAmount());

        assertThat(updated.getUseAmount()).isEqualTo(heat);

    }

    @Test
    void findByYear() {
        final int year = 2023;

        List<PriceBio> byYear = bioService.findByYear(year);
        for (PriceBio bio : byYear) {
            System.out.println(bio.getDate() + "||" + bio.getUseAmount());
        }
    }

    @Test
    void findBy() {
        final int year = 2023;
        repository.findAllByYear(year);
    }
}