package com.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * @author Fall
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddFeedbackDTO {
    @NotBlank(message = "用户id不能为空")
    private String userId;

    @NotBlank(message = "反馈信息不能为空")
    private String information;

    @NotNull(message = "订单id不能为空")
    private Integer orderId;
}
