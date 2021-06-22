package com.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author Fall
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductBean extends BaseBean{
    private Integer id;
    private String name;
    private String image;
    private String type;
    private double price;
    private Integer number;
    private java.sql.Date product_time;
    private int marker;
}
