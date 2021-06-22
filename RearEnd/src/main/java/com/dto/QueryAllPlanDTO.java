package com.dto;

import com.bean.ProductBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author Fall
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryAllPlanDTO {
    private Integer planId;
    private Integer employeeId;
    private String name;
    private java.sql.Date planTime;
    private List<ProductBean> productBeanList;
    private String status;
}
