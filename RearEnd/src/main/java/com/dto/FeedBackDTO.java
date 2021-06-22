package com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Fall
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedBackDTO {
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @NotBlank(message = "反馈信息不能为空")
    private String information;

    @NotNull(message = "订单id不能为空")
    private Integer orderId;
}
