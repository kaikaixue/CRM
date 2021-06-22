package com.service.Impl;

import com.dto.*;
import com.utils.ReturnCode;
import com.vo.ReturnVO;
import com.bean.*;
import com.mapper.*;
import com.service.*;
import com.utils.Tools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fall
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private PlanMapper planMapper;

    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public ReturnVO queryUserAllOrder(QueryAllOrderDTO queryAllOrderDTO) {
        if (userMapper.queryUserId(queryAllOrderDTO.getUserId(), 2) == null) {
            return new ReturnVO(ReturnCode.FAIL, "用户不存在");
        }

        List<OrderDTO> orderDTOS = orderMapper.queryUserAllOrder(queryAllOrderDTO.getUserId());

        if (orderDTOS == null) {
            return new ReturnVO(ReturnCode.FAIL, "查找失败");
        }

        List<QueryAllOrderDisplayDTO> list = new ArrayList<>();

        for (int i = 0; i < orderDTOS.size(); i++) {
            OrderDTO orderDTO = orderDTOS.get(i);
            ProductBean productBean = productMapper.queryProduct(orderDTO.getProduct_id());
            PlanBean planBean = planMapper.queryPlan(orderDTO.getPlan_id());
            list.add(new QueryAllOrderDisplayDTO(
                    orderDTO.getId(),
                    productBean.getName(),
                    orderDTO.getNumber(),
                    productBean.getPrice(),
                    orderDTO.getNumber() * productBean.getPrice(),
                    planBean.getEmployee_id(),
                    orderDTO.getPhase() == 0 ? "未完成" : "已完成",
                    feedbackMapper.queryOrderFeedback(orderDTO.getId()) == null ? false : true,
                    null
                    ));
        }

        return new ReturnVO(list);
    }

    @Override
    public ReturnVO addOrder(AddOrderDTO addOrderDTO) {
        if (productMapper.queryProduct(addOrderDTO.getProductId()).getMarker() == 0 ||
                userMapper.queryUserId(addOrderDTO.getUserId(), 2) == null) {
            return new ReturnVO(ReturnCode.FAIL, "错误");
        }

        PlanBean planBean = productMapper.queryProductPlan(addOrderDTO.getProductId());

        Integer num = orderMapper.addOrder(addOrderDTO.getProductId(), addOrderDTO.getUserId(), planBean.getId(), 0, addOrderDTO.getNumber(), Tools.getDate(), Tools.getDate());

        if (num == null) {
            return new ReturnVO(ReturnCode.FAIL);
        }

        num = orderMapper.queryOrderMaxId();

        OrderDTO orderDTO = orderMapper.queryOrder(num);
        ProductBean productBean = productMapper.queryProduct(orderDTO.getProduct_id());

        return new ReturnVO(new OrderDisplayDTO(
                orderDTO.getId(),
                productBean.getName(),
                orderDTO.getNumber(),
                productBean.getPrice(),
                orderDTO.getNumber() * productBean.getPrice(),
                planBean.getEmployee_id(),
                orderDTO.getPhase() == 0 ? "未完成" : orderDTO.getPhase() == 1 ? "已完成" : orderDTO.getPhase() == 2 ? "已取消" : "不同意",
                "未反馈",
                new java.sql.Date(Tools.getDate())
        ));
    }

    @Override
    public ReturnVO deleteUserOrder(DeleteUserOrderDTO deleteUserOrder) {
        if (orderMapper.queryOrder(deleteUserOrder.getOrderId()) == null) {
            return new ReturnVO(ReturnCode.FAIL, "该订单不存在");
        }

        Integer num = orderMapper.deleteUserOrder(deleteUserOrder.getOrderId(), Tools.getDate());

        if (num == null) {
            return new ReturnVO(ReturnCode.FAIL, "更新失败");
        }

        return new ReturnVO();
    }

    @Override
    public ReturnVO queryAllEmployeeOrder(QueryEmployeeOrderDTO queryEmployeeOrderDTO) {
        if (userMapper.queryUserId(queryEmployeeOrderDTO.getUserId(), 1) == null) {
            return new ReturnVO(ReturnCode.FAIL, "用户不存在");
        }

        List<PlanBean> planBeans = planMapper.queryEmployeePlan(queryEmployeeOrderDTO.getUserId());
        List<QueryEmployeeOrderDisplayDTO> list = new ArrayList<>();
        for (PlanBean planBean : planBeans) {
            List<OrderDTO> orderDTOs = orderMapper.queryOrderByPlan(planBean.getId());
            for (OrderDTO orderDTO : orderDTOs) {
                ProductBean productBean = productMapper.queryProduct(orderDTO.getProduct_id());
                list.add(new QueryEmployeeOrderDisplayDTO(
                        orderDTO.getId(),
                        productBean.getName(),
                        orderDTO.getNumber(),
                        productBean.getPrice(),
                        orderDTO.getNumber() * productBean.getPrice(),
                        planBean.getEmployee_id(),
                        orderDTO.getPhase() == 0 ? false : true,
                        feedbackMapper.queryOrderFeedback(orderDTO.getId()) == null ? "未反馈" : "已反馈",
                        orderDTO.getCreate_time()
                ));
            }
        }

        return new ReturnVO(list);
    }

    @Override
    public ReturnVO updateOrderPhase(UpdateOrderPhaseDTO updateOrderPhaseDTO) {
        if (orderMapper.queryOrder(updateOrderPhaseDTO.getOrderId()) == null) {
            return new ReturnVO(ReturnCode.FAIL, "订单不存在");
        }

        if (updateOrderPhaseDTO.getPhase() != 0) {
            return new ReturnVO(ReturnCode.FAIL, "已经提交过了");
        }

        Integer num = orderMapper.updateOrderPhase(updateOrderPhaseDTO.getPhase(), Tools.getDate(), updateOrderPhaseDTO.getOrderId());

        if (num == null) {
            return new ReturnVO(ReturnCode.FAIL, "更新失败");
        }

        return new ReturnVO(orderMapper.queryOrder(updateOrderPhaseDTO.getOrderId()));
    }
}
