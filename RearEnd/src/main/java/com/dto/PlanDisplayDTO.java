package com.dto;

import com.bean.ProductBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fall
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanDisplayDTO {
    private Integer planId;
    private String planName;
    private List<ProductBean> productBeanList;
    private Double actualPerformance;
    private Double targetPerformance;
    private Double gap;
    private Boolean status;
    private java.sql.Date startTime;
    private java.sql.Date endTime;
}
