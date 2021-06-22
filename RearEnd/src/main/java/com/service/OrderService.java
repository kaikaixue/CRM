package com.service;

import com.dto.*;
import com.vo.ReturnVO;

/**
 * @author Fall
 */
public interface OrderService {
    /**
     * 查询所有有关这个用户的订单信息
     * @param queryAllOrderDTO QueryAllOrderDTO
     * @return ReturnVO
     */
    ReturnVO queryUserAllOrder(QueryAllOrderDTO queryAllOrderDTO);

    /**
     * 增加订单
     * @param addOrderDTO AddOrderDTO
     * @return ReturnVO
     */
    ReturnVO addOrder(AddOrderDTO addOrderDTO);

    /**
     * 用户删除订单
     * @param deleteUserOrder DeleteUserOrderDTO
     * @return ReturnVO
     */
    ReturnVO deleteUserOrder(DeleteUserOrderDTO deleteUserOrder);

    /**
     * 查询雇员的所有订单
     * @param queryEmployeeOrderDTO QueryEmployeeOrderDTO
     * @return ReturnVO
     */
    ReturnVO queryAllEmployeeOrder(QueryEmployeeOrderDTO queryEmployeeOrderDTO);

    /**
     * 更新订单状态
     * @param updateOrderPhaseDTO UpdateOrderPhaseDTO
     * @return ReturnVO
     */
    ReturnVO updateOrderPhase(UpdateOrderPhaseDTO updateOrderPhaseDTO);
}
