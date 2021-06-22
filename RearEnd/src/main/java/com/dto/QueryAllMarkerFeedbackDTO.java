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
public class QueryAllMarkerFeedbackDTO {
    @NotNull(message = "标识码不能为空")
    @Max(value = 2, message = "标识码应为1或2")
    @Min(value = 1, message = "标识码应为1或2")
    private Integer marker;
}
