package com.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Fall
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserOrderDTO {
    @NotNull(message = "订单id不能为空")
    private Integer orderId;
}
