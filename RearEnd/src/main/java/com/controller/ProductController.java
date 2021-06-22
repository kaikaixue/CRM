package com.controller;


import com.dto.InsertProductDTO;
import com.service.ProductService;
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
@Api(tags = "产品接口")
public class ProductController {
    @Resource
    private ProductService productService;

    @RequestMapping("/queryAllProduct")
    @ApiOperation(value = "查询所有产品接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryAllProduct() {
        return productService.queryAllProduct();
    }

    @RequestMapping("/insertProduct")
    @ApiOperation(value = "增加产品接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO insertProduct(@RequestBody @Validated InsertProductDTO insertProductDTO) {
        return productService.insertProduct(insertProductDTO);
    }

    @RequestMapping("/queryPlanAllProduct")
    @ApiOperation(value = "查看所有可以添加计划的商品接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryPlanAllProduct() {
        return productService.queryPlanAllProduct();
    }

    @RequestMapping("/queryBuyAllProduct")
    @ApiOperation(value = "查看所有可以买的商品接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryBuyAllProduct() {
        return productService.queryBuyAllProduct();
    }

    @RequestMapping("/queryAllProductType")
    @ApiOperation(value = "查看所有商品类型接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryAllProductType() {
        return productService.queryAllProductType();
    }
}
