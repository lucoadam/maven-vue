package com.lucoadam.hms.metas;

public interface MetaService {
    Meta findByKey(String key);

    Iterable<Meta> findAll();

    void updateByKey(String key, String text);

    Iterable<Meta> findForSettingPage();

    Boolean usedSupplementaryMarksFlag();

    Integer getOperatingYear();
}
