package com.controller;

import com.dto.InsertPlanDTO;
import com.dto.QueryEmployeePlanDTO;
import com.dto.UpdateEmployeePlanDTO;
import com.service.PlanService;
import com.vo.ReturnVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Fall
 */
@RestController
@Api(tags = "计划接口")
public class PlanController {
    @Resource
    private PlanService planService;

    @RequestMapping("/queryEmployeePlan")
    @ApiOperation(value = "查找员工计划接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryEmployeePlan(@RequestBody @Validated QueryEmployeePlanDTO queryEmployeePlanDTO) {
        return planService.queryEmployeePlan(queryEmployeePlanDTO);
    }

    @RequestMapping("/updateEmployeePlan")
    @ApiOperation(value = "员工提交计划接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO updateEmployeePlan(@RequestBody @Validated UpdateEmployeePlanDTO updateEmployeePlanDTO) {
        return planService.updateEmployeePlan(updateEmployeePlanDTO);
    }

    @RequestMapping("/queryAllPlan")
    @ApiOperation(value = "查询所有计划接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryAllPlan() {
        return planService.queryAllPlan();
    }

    @RequestMapping("/insertProductPlan")
    @ApiOperation(value = "增加计划接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO insertPlan(@RequestBody @Validated InsertPlanDTO planDTO) {
        return planService.insertPlan(planDTO);
    }

}
