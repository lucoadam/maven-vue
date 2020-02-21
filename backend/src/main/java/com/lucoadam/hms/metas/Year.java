package com.lucoadam.hms.metas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "years")
public class Year implements Serializable {

    private static final long serialVersionUID = -1285561867596014659L;

    private Integer year;

    @Id
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Year year1 = (Year) o;

        if (year != null ? !year.equals(year1.year) : year1.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return year != null ? year.hashCode() : 0;
    }
}
