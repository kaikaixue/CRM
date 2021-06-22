package com.dto;

import com.bean.BaseBean;
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
public class OrderDTO extends BaseBean {
    private Integer id;
    private Integer product_id;
    private Integer user_id;
    private Integer plan_id;
    private Integer phase;
    private Integer number;
    private java.sql.Date create_time;
}
