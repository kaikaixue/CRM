package com.service;

import com.dto.InsertProductDTO;
import com.vo.ReturnVO;

/**
 * @author Fall
 */
public interface ProductService {
    /**
     * 查询所有产品
     * @return ReturnVO
     */
    ReturnVO queryAllProduct();

    /**
     * 增加产品
     * @param insertProductDTO InsertProductDTO
     * @return ReturnVO
     */
    ReturnVO insertProduct(InsertProductDTO insertProductDTO);

    /**
     * 查询所有产品 (和上面不一样)
     * @return ReturnVO
     */
    ReturnVO queryBuyAllProduct();

    /**
     * 查询所有产品的类型
     * @return ReturnVO
     */
    ReturnVO queryAllProductType();

    /**
     * a
     * @return ReturnVO
     */
    ReturnVO queryPlanAllProduct();
}
