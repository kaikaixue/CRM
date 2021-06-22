package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Fall
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsertPlanDTO {
    @NotBlank(message = "计划名字不能为空")
    private String name;

    @NotNull(message = "雇员id不能为空")
    private Integer employeeId;

    @NotBlank(message = "计划时间不能为空")
    private String planTime;

    @NotEmpty(message = "产品列表不能为空")
    private List<ProductPlanDTO> list;
}
