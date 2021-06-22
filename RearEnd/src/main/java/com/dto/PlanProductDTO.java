package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fall
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanProductDTO {
    private Integer id;
    private Integer product_id;
    private Integer plan_id;
    private Integer number;
}
