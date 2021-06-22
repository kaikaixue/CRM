package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fall
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryEmployeeOrderDisplayDTO {
    private Integer orderId;
    private String productName;
    private Integer number;
    private Double unitPrice;
    private Double totalPrice;
    private Integer employeeId;
    private Boolean orderStatus;
    private String feedbackStatus;
    private java.sql.Date createTime;
}
