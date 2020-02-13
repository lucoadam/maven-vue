package com.lucoadam.hms.products;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal price;
}