package com.service.Impl;

import com.bean.ProductBean;
import com.utils.ReturnCode;
import com.dto.InsertProductDTO;
import com.mapper.ProductMapper;
import com.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public ReturnVO queryAllProduct() {
        return new ReturnVO(productMapper.queryAllProduct());
    }

    @Override
    public ReturnVO insertProduct(InsertProductDTO insertProductDTO)  {
        if (productMapper.queryProductTypeName(insertProductDTO.getType(), insertProductDTO.getName()) != null) {
            return new ReturnVO(ReturnCode.FAIL, "该商品已存在");
        }

        String[] temps = insertProductDTO.getProductTime().split("-");
        String productTime = "";
        for (int i = 0; i < temps.length; i++) {
            if (i >= 1 && temps[i].length() == 1) {
                temps[i] = "0" + temps[i];
            }
            productTime += temps[i];
        }

        productTime += "000000";

        Integer num = productMapper.insertProduct(
                insertProductDTO.getName(),
                "https://train-homework.oss-cn-shanghai.aliyuncs.com/ProductImage/104.jpg",
                insertProductDTO.getType(),
                Double.parseDouble(insertProductDTO.getPrice()),
                0,
                Long.parseLong(productTime),
                Tools.getDate(),
                Tools.getDate());

        if (num == null) {
            return new ReturnVO(ReturnCode.FAIL, "插入失败");
        }

        return new ReturnVO(productMapper.queryProduct(productMapper.queryProductMaxId()));
    }

    @Override
    public ReturnVO queryBuyAllProduct() {
        List<ProductBean> productBeanList = productMapper.queryMakerProduct();

        if (productBeanList == null) {
            return new ReturnVO(ReturnCode.FAIL, "查找失败");
        }

        List<ProductBean> list = new ArrayList<>();
        for (ProductBean productBean : productBeanList) {
            list.add(productBean);
        }

        return new ReturnVO(list);
    }

    @Override
    public ReturnVO queryAllProductType() {
        return new ReturnVO(productMapper.queryAllProductType());
    }

    @Override
    public ReturnVO queryPlanAllProduct() {
        return new ReturnVO(productMapper.queryPlanAllProduct());
    }
}
