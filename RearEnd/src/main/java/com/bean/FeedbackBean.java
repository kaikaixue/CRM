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
public class FeedbackBean extends BaseBean{
    private Integer user_id;
    private Integer order_id;
    private String information;
    private Integer tradition;
}
