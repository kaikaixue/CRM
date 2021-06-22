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
public class DeleteUserDTO {
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @NotNull(message = "状态码不能为空")
    @Max(value = 2, message = "标记码应在(0~2)之间")
    @Min(value = 0, message = "标记码应在(0~2)之间")
    private Integer marker;
}
