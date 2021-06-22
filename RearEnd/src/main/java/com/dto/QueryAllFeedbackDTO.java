package com.dto;

import com.bean.ProductBean;
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
public class QueryAllFeedbackDTO {
    private Integer user_id;
    private ProductBean productBean;
    private String information;
    private String tradition;
    private String name;
    private String department;
    private String type;
}
