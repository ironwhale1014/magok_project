package com.honeybee.magok.controller;

import com.honeybee.magok.dto.BioViewFourYearResponse;
import com.honeybee.magok.dto.PriceBioResponse;
import com.honeybee.magok.dto.YearResponse;
import com.honeybee.magok.service.BioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class BioViewController {

    private final BioService bioService;

    @GetMapping("/bio")
    public String getBio(@RequestParam(required = false) Integer year, Model model) {

        if (year != null) {

            List<BioViewFourYearResponse> bioYearResponse = bioService.getYearsResponse(year);

            model.addAttribute("bioYearResponse", bioYearResponse);

            return "bioPrice";
        } else {
            return "bioPrice";
        }


    }
}
