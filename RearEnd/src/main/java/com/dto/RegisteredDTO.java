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
public class RegisteredDTO {
    @NotBlank(message = "账号不能为空")
    @Max(value = 10, message = "账号长度应在(6~10)之间")
    @Min(value = 6, message = "账号长度应在(6~10)之间")
    private String account;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "[1-9|a-z|A-Z]{8,16}", message = "密码因为数字或字母的组合")
    private String password;

    @NotBlank(message = "名字不能为空")
    private String name;

    @NotNull(message = "状态码不能为空")
    @Max(value = 2, message = "标记码应在(0~2)之间")
    @Min(value = 0, message = "标记码应在(0~2)之间")
    private Integer marker;
}
