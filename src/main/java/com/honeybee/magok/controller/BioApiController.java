package com.honeybee.magok.controller;


import com.honeybee.magok.domain.PriceBio;
import com.honeybee.magok.dto.AddPriceBioRequest;
import com.honeybee.magok.dto.BioViewFourYearResponse;
import com.honeybee.magok.dto.PriceBioResponse;
import com.honeybee.magok.dto.YearResponse;
import com.honeybee.magok.service.BioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BioApiController {
    private final BioService bioService;

    @GetMapping("/api/bio/{year}")
    public ResponseEntity<YearResponse<PriceBioResponse>> getBioByYear(@PathVariable int year) {

        YearResponse<PriceBioResponse> bioYearResponse = bioService.getYearResponseApi(year);


        return ResponseEntity.ok().body(bioYearResponse);

    }

    @GetMapping("/api/bio")
    public ResponseEntity<List<PriceBioResponse>> getBioPriceAll() {
        List<PriceBioResponse> bioResponses = bioService.findAll().stream().map(PriceBioResponse::new).toList();
        return ResponseEntity.ok().body(bioResponses);
    }

    @PostMapping("/api/bio")
    public ResponseEntity<PriceBio> addPriceBio(@RequestBody AddPriceBioRequest request) {

        PriceBio saved = bioService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
