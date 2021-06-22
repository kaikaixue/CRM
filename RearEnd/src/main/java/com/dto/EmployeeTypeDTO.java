package com.dto;

import io.swagger.annotations.Api;
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
public class EmployeeTypeDTO {
    private Integer id;
    private String type;
}
