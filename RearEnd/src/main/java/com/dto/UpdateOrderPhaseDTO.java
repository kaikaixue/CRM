package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Fall
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderPhaseDTO {
    @NotNull(message = "产品id不能为空")
    private Integer orderId;

    @NotNull(message = "状态码不能为空")
    @Max(value = 3, message = "状态码应在(0~3)之间")
    @Min(value = 0, message = "状态码应在(0~3)之间")
    private Integer phase;
}
