package com.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author Fall
 */
@Data
@Builder
public class PlanProduct extends BaseBean {
    private int product_id;
    private int plan_id;
    private int number;
}
