package com.lucoadam.hms.metas;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearRepository extends CrudRepository<Year, Integer> {
}
