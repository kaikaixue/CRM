package com.service.Impl;

import com.bean.PlanBean;
import com.bean.ProductBean;
import com.utils.ReturnCode;
import com.dto.*;
import com.mapper.OrderMapper;
import com.mapper.PlanMapper;
import com.mapper.ProductMapper;
import com.mapper.UserMapper;
import com.service.PlanService;
import com.utils.Tools;
import com.vo.ReturnVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fall
 */
@Service
public class PlanServiceImpl implements PlanService {
    @Resource
    private PlanMapper planMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderMapper orderMapper;

    @Override
    public ReturnVO queryEmployeePlan(QueryEmployeePlanDTO queryEmployeePlanDTO) {
        if (userMapper.queryUserId(queryEmployeePlanDTO.getEmployeeId(), 1) == null) {
            return new ReturnVO(ReturnCode.FAIL, "该用户不存在");
        }

        List<PlanBean> planBeans = planMapper.queryEmployeePlan(queryEmployeePlanDTO.getEmployeeId());

        if (planBeans == null) {
            return new ReturnVO(ReturnCode.FAIL, "查找失败");
        }

        List<PlanDisplayDTO> list = new ArrayList<>();
        for (int i  = 0; i < planBeans.size(); i++) {
            Double targetPerformance = 0.0;
            Double actualPerformance = 0.0;
            PlanBean planBean = planBeans.get(i);
            //查询出的列表是计划产品也就是目标业绩
            List<ProductBean> list1 = productMapper.queryPlanProduct(planBean.getId());
            Integer number1 = 0;
            for (int j = 0; j < list1.size(); j++) {
                //查询目标计划的各个产品的数量
                List<PlanProductDTO> planProductDTOList = productMapper.queryTPlanProduct(list1.get(j).getId(), planBean.getId());
                for (PlanProductDTO planProductDTO : planProductDTOList) {
                    number1 += planProductDTO.getNumber();
                }
                targetPerformance += list1.get(j).getPrice() * number1;
                list1.get(j).setNumber(number1);
            }

            //查询出的列表是计划产品也就是实际业绩
            List<ProductBean> list2 = productMapper.queryPlanOrder(planBean.getId());
            for (int j = 0; j < list2.size(); j++) {
                List<OrderDTO> orderDTOList = orderMapper.queryPlanOrder(list2.get(j).getId(), planBean.getId());
                Integer number = 0;
                for (OrderDTO orderDTO : orderDTOList) {
                    number += orderDTO.getNumber();
                }
                actualPerformance += list2.get(j).getPrice() * number;
            }

            list.add(new PlanDisplayDTO(
                    planBean.getId(),
                    planBean.getName(),
                    list1,
                    actualPerformance,
                    targetPerformance,
                    Math.abs(targetPerformance - actualPerformance),
                    planBean.getComplete() == 0 ? false : true,
                    planBean.getCreate_time(),
                    planBean.getPlan_time()
                    ));
        }

        return new ReturnVO(list);
    }

    @Override
    public ReturnVO updateEmployeePlan(UpdateEmployeePlanDTO updateEmployeePlanDTO) {
        if (planMapper.queryPlan(updateEmployeePlanDTO.getPlanId()) == null) {
            return new ReturnVO(ReturnCode.FAIL, "订单不存在");
        }

        List<PlanDisplayDTO> list = new ArrayList<>();
        Double targetPerformance = 0.0;
        Double actualPerformance = 0.0;
        //查询出的列表是计划产品也就是目标业绩
        List<ProductBean> list1 = productMapper.queryPlanProduct(updateEmployeePlanDTO.getPlanId());
        for (int j = 0; j < list1.size(); j++) {
            //查询目标计划的各个产品的数量
            List<PlanProductDTO> planProductDTOList = productMapper.queryTPlanProduct(list1.get(j).getId(), updateEmployeePlanDTO.getPlanId());
            productMapper.updateProductMarker(list1.get(j).getId(), 0, Tools.getDate());
            Integer number = 0;
            for (PlanProductDTO planProductDTO : planProductDTOList) {
                number += planProductDTO.getNumber();
            }
            targetPerformance += list1.get(j).getPrice() * number;
            list1.get(j).setNumber(number);
        }

        //查询出的列表是计划产品也就是实际业绩
        List<ProductBean> list2 = productMapper.queryPlanOrder(updateEmployeePlanDTO.getPlanId());
        for (int j = 0; j < list2.size(); j++) {
            List<OrderDTO> orderDTOList = orderMapper.queryPlanOrder(list2.get(j).getId(), updateEmployeePlanDTO.getPlanId());
            Integer number = 0;
            for (OrderDTO orderDTO : orderDTOList) {
                number += orderDTO.getNumber();
            }
            actualPerformance += list2.get(j).getPrice() * number;
        }

        if (actualPerformance >= targetPerformance) {
            planMapper.updateEmployeePlan(1, updateEmployeePlanDTO.getPlanId(), Tools.getDate());
        } else {
            planMapper.updateEmployeePlan(2, updateEmployeePlanDTO.getPlanId(), Tools.getDate());
        }

        return new ReturnVO();
    }

    @Override
    public ReturnVO queryAllPlan() {
        List<PlanBean> planBeans = planMapper.queryAllPlan();

        List<QueryAllPlanDTO> list = new ArrayList<>();
        for (int i = 0; i < planBeans.size(); i++) {
            PlanBean planBean = planBeans.get(i);
            list.add(new QueryAllPlanDTO(
                    planBean.getId(),
                    planBean.getEmployee_id(),
                    planBean.getName(),
                    planBean.getPlan_time(),
                    productMapper.queryPlanProduct(planBean.getId()),
                    planBean.getComplete() == 0 ? "未完成" : planBean.getComplete() == 1 ? "完成" : "完成但是没有完全完成"
                    ));
        }

        QueryAllPlanDTO queryAllPlanDTO = new QueryAllPlanDTO();

        return new ReturnVO(list);
    }

    @Override
    public ReturnVO insertPlan(InsertPlanDTO planDTO) {
        for (ProductPlanDTO productPlanDTO : planDTO.getList()) {
            if (productMapper.queryProduct(productPlanDTO.getProductId()) == null) {
                return new ReturnVO(ReturnCode.FAIL, "产品不存在");
            }

            if (productMapper.queryProductMarker(productPlanDTO.getProductId(), 1) != null) {
                return new ReturnVO(ReturnCode.FAIL, "该商品已被使用");
            }
        }

        String[] temps = planDTO.getPlanTime().split("-");
        String planTime = "";
        for (int i = 0; i < temps.length; i++) {
            if (i >= 1 && temps[i].length() == 1) {
                temps[i] = "0" + temps[i];
            }
            planTime += temps[i];
        }

        planTime += "000000";

        Integer num = planMapper.insertPlan(planDTO.getName(), planDTO.getEmployeeId(), Long.parseLong(planTime), 0, Tools.getDate(), Tools.getDate());

        if (num == null) {
            return new ReturnVO(ReturnCode.FAIL, "插入失败");
        }

        Integer maxId = planMapper.queryPlanMaxId();

        for (ProductPlanDTO productPlanDTO : planDTO.getList()) {
            num = planMapper.insertProductPlan(productPlanDTO.getProductId(), maxId, productPlanDTO.getNumber(), Tools.getDate(), Tools.getDate());
            if (num == null) {
                return new ReturnVO(ReturnCode.FAIL, "插入失败");
            }
            productMapper.updateProductMarker(productPlanDTO.getProductId(), 1, Tools.getDate());
        }

        PlanBean planBean = planMapper.queryPlan(planMapper.queryPlanMaxId());

        return new ReturnVO(new QueryAllPlanDTO(
                planBean.getId(),
                planBean.getEmployee_id(),
                planBean.getName(),
                planBean.getPlan_time(),
                productMapper.queryPlanProduct(planBean.getId()),
                planBean.getComplete() == 0 ? "未完成" : planBean.getComplete() == 1 ? "完成" : "完成但是没有完全完成"
        ));
    }
}
