package com.mapper;

import com.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * @author Fall
 */
@Mapper
public interface OrderMapper {
    /**
     * 用户订单信息
     * @param productId Integer
     * @param userId Integer
     * @param planId Integer
     * @param phase Integer
     * @param number Integer
     * @param createTime Long
     * @param updateTime Long
     * @return Integer
     */
    Integer addOrder(Integer productId, Integer userId, Integer planId, Integer phase, Integer number, Long createTime, Long updateTime);

    /**
     * 用户删除订单
     * @param id Integer
     * @param updateTime Long
     * @return Integer
     */
    Integer deleteUserOrder(Integer id, Long updateTime);

    /**
     * 查询所有订单
     * @param id Integer
     * @return OrderDTO
     */
    OrderDTO queryOrder(Integer id);

    /**
     * 查询订单中计划相同的
     * @param productId Integer
     * @param planId Integer
     * @return List<OrderDTO>
     */
    List<OrderDTO> queryPlanOrder(Integer productId, Integer planId);

    /**
     * 查询所有有关这个用户的订单信息
     * @param userId Integer
     * @return java.util.List<OrderDTO>
     */
    java.util.List<OrderDTO> queryUserAllOrder(Integer userId);

    /**
     * 查询最大id
     * @return Integer
     */
    Integer queryOrderMaxId();

    /**
     * 根据计划查询订单
     * @param planId Integer
     * @return java.util.List<OrderDTO>
     */
    java.util.List<OrderDTO> queryOrderByPlan(Integer planId);

    /**
     * 更新订单状态
     * @param phase Integer
     * @param updateTime Long
     * @param id Integer
     * @return Integer
     */
    Integer updateOrderPhase(Integer phase, Long updateTime, Integer id);

    /**
     * 查询被该用户取消的订单
     * @param userId Integer
     * @return List<OrderDTO>
     */
    List<OrderDTO> queryOrderCancel(Integer userId);
}
