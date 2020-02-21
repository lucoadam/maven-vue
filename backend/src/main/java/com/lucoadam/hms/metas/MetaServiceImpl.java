package com.lucoadam.hms.metas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MetaServiceImpl implements MetaService {

    @Autowired
    MetaRepository metaRepository;
    private Boolean usedSupplementaryMarkForCalculation;
    private Integer operatingYear;

    @Override
    public Meta findByKey(String key) {
        return metaRepository.findByKey(key);
    }

    @Override
    public Iterable<Meta> findAll() {
        return metaRepository.findAll();
    }

    @Override
    public void updateByKey(String key, String text) {
        Meta meta = findByKey(key);
        meta.setValue(text);
        metaRepository.save(meta);

        if (key.equals("use_supp_marks_for_calculation")) fetchAndUpdateUsedSupplementaryMarksFlag();
        if (key.equals("operating_year")) fetchAndUpdateOperatingYear();
    }


    @Override
    public Iterable<Meta> findForSettingPage() {
        String[] fields = {"operating_year", "municipal_name", "two_tab_based_entry", "centre_based_entry", "municipal_office_name", "student_entry_lock_date", "practical_entry_lock_date", "result_published", "manual_symbol_number", "th_marks_entry_school_side", "marksheet_type", "province_text", "municipal_office_location", "symbol_listing_report_title", "marksheet_issue_date", "supp_minimum_grade", "supp_at_max_subject", "supp_marks_entry_school_side", "use_supp_marks_for_calculation"};

        return metaRepository.findAllById(Arrays.asList(fields));
    }


    @Override
    public Boolean usedSupplementaryMarksFlag() {
        if (usedSupplementaryMarkForCalculation == null) {
            fetchAndUpdateUsedSupplementaryMarksFlag();
        }

        return usedSupplementaryMarkForCalculation;
    }


    private void fetchAndUpdateUsedSupplementaryMarksFlag() {
        Meta meta = findByKey("use_supp_marks_for_calculation");
        usedSupplementaryMarkForCalculation = meta.getValue() != null && meta.getValue().equals("true");
    }


    private void fetchAndUpdateOperatingYear() {
        Meta meta = findByKey("operating_year");
        operatingYear = Integer.valueOf(meta.getValue());
    }


    public Integer getOperatingYear() {
        if (operatingYear == null)
            fetchAndUpdateOperatingYear();

        return operatingYear;
    }
}
