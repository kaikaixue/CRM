package com.controller;

import com.dto.*;
import com.vo.ReturnVO;
import com.service.OrderService;
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
@Api(tags = "订单接口")
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping("/queryAllOrder")
    @ApiOperation(value = "查询所有订单接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryAllOrder(@RequestBody @Validated QueryAllOrderDTO queryAllOrderDTO) {
        return orderService.queryUserAllOrder(queryAllOrderDTO);
    }

    @RequestMapping("/addOrder")
    @ApiOperation(value = "增加订单接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO addOrder(@RequestBody @Validated AddOrderDTO addOrderDTO) {
        return orderService.addOrder(addOrderDTO);
    }

    @RequestMapping("/deleteUserOrder")
    @ApiOperation(value = "用户删除订单接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO deleteUserOrder(@RequestBody @Validated DeleteUserOrderDTO deleteUserOrder) {
        return orderService.deleteUserOrder(deleteUserOrder);
    }

    @RequestMapping("/queryAllEmployeeOrder")
    @ApiOperation(value = "查询雇员所有订单接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryAllEmployeeOrder(@RequestBody @Validated QueryEmployeeOrderDTO queryEmployeeOrderDTO) {
        return orderService.queryAllEmployeeOrder(queryEmployeeOrderDTO);
    }

    @RequestMapping("/updateOrderPhase")
    @ApiOperation(value = "更新订单接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO updateOrderPhase(@RequestBody @Validated UpdateOrderPhaseDTO updateOrderPhaseDTO) {
        return orderService.updateOrderPhase(updateOrderPhaseDTO);
    }
}
