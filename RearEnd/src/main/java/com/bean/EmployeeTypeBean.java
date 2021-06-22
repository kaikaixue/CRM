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
public class EmployeeTypeBean {
    private Integer id;
    private String type;
    private Integer power;
}
