package com.mapper;

import com.bean.EmployeeTypeBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Fall
 */
@Mapper
public interface EmployeeTypeMapper {
    /**
     * 查询雇员类型的权
     * @param type String
     * @return EmployeeTypeBean
     */
    EmployeeTypeBean queryEmployeeTypePower(String type);
}
