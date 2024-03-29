package com.honeybee.magok.service;


import com.honeybee.magok.domain.PriceBio;
import com.honeybee.magok.dto.*;
import com.honeybee.magok.repository.BioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BioService {

    final private BioRepository bioRepository;

    public PriceBio save(AddPriceBioRequest request) {


        final LocalDate localDate = LocalDate.parse(request.getDate(), DateTimeFormatter.ISO_DATE);
        final Integer useAmount = request.getUseAmount();
        final Integer unit = request.getUnit();
        final String memo = request.getMemo();

        Integer etcPrice = request.getEtcPrice();
        if (etcPrice == null) etcPrice = 0;
        final Integer supplyPrice = useAmount * unit + etcPrice;


        BigDecimal vatFactor = BigDecimal.valueOf(0.1);
        BigDecimal bigDecimalVat = vatFactor.multiply(BigDecimal.valueOf(supplyPrice));
        final Integer vat = bigDecimalVat.setScale(0, RoundingMode.DOWN).intValue();


        PriceBio priceBio = PriceBio.builder().date(localDate).useAmount(useAmount).unit(unit).memo(memo).etcPrice(etcPrice).supplyPrice(supplyPrice).vat(vat).totalPrice(vat + supplyPrice).build();


        return bioRepository.save(priceBio);
    }

    public void delete(Long id) {
        bioRepository.deleteById(id);
    }

    @Transactional
    public PriceBio update(Long id, UpdatePriceBioRequest request) {
        PriceBio findBio = findById(id);
        findBio.update(request.getDate(), request.getUseAmount(), request.getUnit(), request.getEtcPrice(), request.getSupplyPrice(), request.getVat(), request.getTotalPrice(), request.getMemo());
        return findBio;
    }

    public PriceBio findById(Long id) {
        return bioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found %d".formatted(id)));
    }

    public List<PriceBio> findAll() {
        return bioRepository.findAll();
    }

    private PriceBio makeDumyPriceBio(int year, int i) {
        return new PriceBio(LocalDate.parse("%d-%02d-01".formatted(year, i + 1)),
                0, 0, 0, 0, 0, 0, "가짜");
    }

    public List<PriceBio> findByYear(int year) {
        List<PriceBio> byYear = bioRepository.findByYear(year);

        if (byYear.size() == 12) {
            return byYear;
        } else if (byYear.isEmpty()) {
            for (int i = 0; i < 12; i++) {
                byYear.add(i, makeDumyPriceBio(year, i));
            }
            return byYear;
        } else {
            List<PriceBio> dumyList = new ArrayList<>();
            for (int i = 0; i < 12; i++) {

                dumyList.add(makeDumyPriceBio(year, i));

            }

            for (PriceBio priceBio : byYear) {
                int index = priceBio.getDate().getMonthValue() - 1;
                dumyList.add(index, priceBio);
            }
            return dumyList;
        }


    }

    public YearResponse<PriceBioResponse> getYearResponseApi(int year) {
        List<PriceBioResponse> first = findByYear(year).stream().map(PriceBioResponse::new).toList();
        List<PriceBioResponse> second = findByYear(year - 1).stream().map(PriceBioResponse::new).toList();
        List<PriceBioResponse> third = findByYear(year - 2).stream().map(PriceBioResponse::new).toList();
        List<PriceBioResponse> forth = findByYear(year - 3).stream().map(PriceBioResponse::new).toList();

        return YearResponse.<PriceBioResponse>builder().first(first).second(second).third(third).forth(forth).build();
    }

    public List<ViewFourYearResponse<PriceBioResponse>> getYearsResponse(int year) {


        YearResponse<PriceBioResponse> bioYearResponse = getYearResponseApi(year);

        List<ViewFourYearResponse<PriceBioResponse>> bioViewFourYearResponses = new ArrayList<>();
        for (int i = 0; i < 12; i++) {

            PriceBioResponse first1 = bioYearResponse.getFirst().get(i);
            PriceBioResponse second1 = bioYearResponse.getSecond().get(i);
            PriceBioResponse third1 = bioYearResponse.getThird().get(i);
            PriceBioResponse forth1 = bioYearResponse.getForth().get(i);


            bioViewFourYearResponses.add(ViewFourYearResponse.<PriceBioResponse>builder().month(i + 1).first(first1).second(second1).third(third1).forth(forth1).build());


        }

        return bioViewFourYearResponses;
    }
}
