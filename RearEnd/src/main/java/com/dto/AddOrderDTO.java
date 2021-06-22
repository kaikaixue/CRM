package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Fall
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderDTO {
    @NotNull(message = "产品id不能为空")
    private Integer productId;

    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @NotNull(message = "数量不能为空")
    @Max(value = 9999, message = "数量应在(1~9999)之间")
    @Min(value = 1, message = "数量应在(1~9999)之间")
    private Integer number;
}
