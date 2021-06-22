package com.mapper;

import com.bean.PlanBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Fall
 */
@Mapper
public interface PlanMapper {
    /**
     * 查找计划
     * @param id Integer
     * @return PlanBean
     */
    PlanBean queryPlan(Integer id);

    /**
     * 更新雇员计划
     * @param complete Integer
     * @param id Integer
     * @param updateTime Long
     * @return Integer
     */
    Integer updateEmployeePlan(Integer complete, Integer id, Long updateTime);

    /**
     * 查询员工计划
     * @param employeeId Integer
     * @return Plane
     */
    List<PlanBean> queryEmployeePlan(Integer employeeId);

    /**
     * 查询所有的计划
     * @return List<PlanBean>
     */
    List<PlanBean> queryAllPlan();

    /**
     * 插入计划
     * @param name String
     * @param employeeId Integer
     * @param planTime Long
     * @param complete Integer
     * @param createTime Long
     * @param updateTime Long
     * @return Integer
     */
    Integer insertPlan(String name, Integer employeeId, Long planTime, Integer complete, Long createTime, Long updateTime);

    /**
     * 插入计划与产品的关系
     * @param productId Integer
     * @param planId Integer
     * @param number Integer
     * @param createTime Long
     * @param updateTime Long
     * @return Integer
     */
    Integer insertProductPlan(Integer productId, Integer planId, Integer number, Long createTime, Long updateTime);

    /**
     * 查询出当前t_plan中id最大值
     * @return Integer
     */
    Integer queryPlanMaxId();
}
