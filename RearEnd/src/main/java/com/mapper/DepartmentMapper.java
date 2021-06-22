package com.mapper;

import com.bean.DepartmentBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Fall
 */
@Mapper
public interface DepartmentMapper {
    /**
     * 查询部门的权
     * @param department String
     * @return DepartmentBean
     */
    DepartmentBean queryDepartmentPower(String department);
}
