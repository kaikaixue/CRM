package com.mapper;

import com.bean.*;
import com.dto.OrderDTO;
import com.dto.PlanProductDTO;
import com.dto.ProductTypeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Fall
 */
@Mapper
public interface ProductMapper {
    /**
     * 查询产品
     * @param id Integer
     * @return ProductBean
     */
    ProductBean queryProduct(Integer id);

    /**
     * 查询所有产品
     * @return List<ProductBean>
     */
    List<ProductBean> queryAllProduct();

    /**
     * 查询产品和计划之间的联系是否存在
     * @param productId Integer
     * @return PlanBean
     */
    PlanBean queryProductPlan(Integer productId);

    /**
     * 查询计划产品
     * @param planId Integer
     * @return List<ProductBean>
     */
    List<ProductBean> queryPlanProduct(Integer planId);

    /**
     * 查询订单中计划下的订单
     * @param planId Integer
     * @return List<OrderDTO>
     */
    List<ProductBean> queryPlanOrder(Integer planId);

    /**
     * 查询计划和产品表
     * @param planId Integer
     * @param productId Integer
     * @return List<PlanProductDTO>
     */
    List<PlanProductDTO> queryTPlanProduct(Integer productId, Integer planId);

    /**
     * 更新产品marker
     * @param id Integer
     * @param marker Integer
     * @param updateTime updateTime
     * @return Integer
     */
    Integer updateProductMarker(Integer id, Integer marker, Long updateTime);

    /**
     * 增加产品
     * @param name String
     * @param image String
     * @param type String
     * @param price Double
     * @param marker Integer
     * @param productTime Long
     * @param createTime Long
     * @param updateTime Long
     * @return Integer
     */
    Integer insertProduct(String name, String image, String type, Double price, Integer marker, Long productTime, Long createTime, Long updateTime);

    /**
     * 查询是否有重复的产品
     * @param type String
     * @param name String
     * @return UserBean
     */
    ProductBean queryProductTypeName(String type, String name);

    /**
     * 查询该商品是否被使用
     * @param id Integer
     * @param marker Integer
     * @return UserBean
     */
    ProductBean queryProductMarker(Integer id, Integer marker);

    /**
     * 查询所有没有被使用的产品
     * @return List<ProductBean>
     */
    List<ProductBean> queryMakerProduct();

    /**
     * 查询所有产品的类型
     * @return List<ProductTypeDTO>
     */
    List<ProductTypeDTO> queryAllProductType();

    /**
     * 查看最大id
     * @return Integer
     */
    Integer queryProductMaxId();

    /**
     * 查看所有可以添加计划的商品接口
     * @return List<ProductBean>
     */
    List<ProductBean> queryPlanAllProduct();
}
