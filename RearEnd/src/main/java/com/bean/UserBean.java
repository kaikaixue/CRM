package com.bean;

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
public class UserBean extends BaseBean{
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String sex;
    private String avatar;
    private String phone;
    private String address;
    private Double cred;
    private Double speed_time;
    private String type;
    private String department;
    private Double wages;
    private Integer marker;
    private String salt;
    private Integer status;
}
