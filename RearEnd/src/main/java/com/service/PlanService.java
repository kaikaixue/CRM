package com.service;

import com.dto.InsertPlanDTO;
import com.dto.QueryEmployeePlanDTO;
import com.dto.UpdateEmployeePlanDTO;
import com.vo.ReturnVO;

/**
 * @author Fall
 */
public interface PlanService {
    /**
     * 查询雇员订单
     * @param queryEmployeePlanDTO QueryEmployeePlanDTO
     * @return ReturnVO
     */
    ReturnVO queryEmployeePlan(QueryEmployeePlanDTO queryEmployeePlanDTO);

    /**
     * 更新订单状态
     * @param updateEmployeePlanDTO UpdateEmployeePlanDTO
     * @return ReturnVO
     */
    ReturnVO updateEmployeePlan(UpdateEmployeePlanDTO updateEmployeePlanDTO);

    /**
     * 查询所有的计划
     * @return ReturnVO
     */
    ReturnVO queryAllPlan();

    /**
     * 增加计划
     * @param planDTO InsertPlanDTO
     * @return ReturnVO
     */
    ReturnVO insertPlan(InsertPlanDTO planDTO);
}
