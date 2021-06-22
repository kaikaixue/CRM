package com.bean;

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
public class PlanBean extends BaseBean{
    private Integer id;
    private String name;
    private Integer employee_id;
    private java.sql.Date create_time;
    private java.sql.Date plan_time;
    private Integer complete;
}
