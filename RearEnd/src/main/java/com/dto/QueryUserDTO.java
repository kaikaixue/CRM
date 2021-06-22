package com.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * @author Fall
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryUserDTO {
    private Integer userId;
    private String account;
    private String name;
    private String sex;
    private String avatar;
    private String phone;
    private String address;
    private Double cred;
    private String speed_time;
    private List<OrderDisplayDTO> orderList;
}
