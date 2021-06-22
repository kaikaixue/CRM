package com.dto;

import com.bean.BaseBean;
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
public class UpdateUserDTO extends BaseBean {
    @NotBlank(message = "用户id不能为空")
    private Integer userId;

    @NotBlank(message = "账号不能为空")
    @Max(value = 10, message = "账号长度应在(6~10)之间")
    @Min(value = 6, message = "账号长度应在(6~10)之间")
    private String account;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "[1-9|a-z|A-Z]{8,16}", message = "密码因为数字或字母的组合")
    private String password;

    @NotBlank(message = "账号不能为空")
    private String name;

    @NotBlank(message = "性别不能为空")
    private String sex;

    @NotBlank(message = "头像不能为空")
    private String avatar;

    @NotBlank(message = "手机号码不能为空")
    private String phone;

    @NotBlank(message = "地址不能为空")
    private String address;

    @NotNull(message = "信用度不能为空")
    @DecimalMax(value = "100", message = "信用度应在(0~100)之间")
    @DecimalMin(value = "0", message = "信用度应在(0~100)之间")
    private Double cred;

    @NotNull(message = "消费时间不能为空")
    @DecimalMin(value = "0", message = "消费时间至少为0")
    private Double speed_time;

    @NotBlank(message = "类型不能为空")
    private String type;

    @NotBlank(message = "部门不能为空")
    private String department;

    @NotNull(message = "工资不能为空")
    private Double wages;

    @NotNull(message = "状态码不能为空")
    @Max(value = 2, message = "标记码应在(0~2)之间")
    @Min(value = 0, message = "标记码应在(0~2)之间")
    private Integer marker;

    private String salt;
}
