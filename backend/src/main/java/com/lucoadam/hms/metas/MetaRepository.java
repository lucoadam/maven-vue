package com.lucoadam.hms.metas;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends PagingAndSortingRepository<Meta, String> {
    Meta findByKey(String key);
}
