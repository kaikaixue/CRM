package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Fall
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsertProductDTO {
    @NotBlank(message = "产品名字不能为空")
    private String name;

    @NotBlank(message = "产品图片不能为空")
    private String image;

    @NotBlank(message = "产品类型不能为空")
    private String type;

    @NotBlank(message = "产品价格不能为空")
    private String price;

    @NotBlank(message = "产品生产时间")
    private String productTime;
}
