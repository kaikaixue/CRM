package com.service.Impl;

import com.bean.FeedbackBean;
import com.bean.UserBean;
import com.dto.*;
import com.mapper.OrderMapper;
import com.mapper.ProductMapper;
import com.mapper.UserMapper;
import com.utils.ReturnCode;
import com.mapper.FeedbackMapper;
import com.service.FeedbackService;
import com.utils.Tools;
import com.vo.ReturnVO;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fall
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    private FeedbackMapper feedbackMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderMapper orderMapper;

    @Override
    public ReturnVO addFeedback(AddFeedbackDTO addFeedbackDTO) {
        if (addFeedbackDTO.getOrderId() != 0) {
            OrderDTO orderDTO = orderMapper.queryOrder(addFeedbackDTO.getOrderId());
            if (orderDTO == null) {
                return new ReturnVO(ReturnCode.FAIL, "产品不存在");
            }

            if (feedbackMapper.queryOrderFeedback(addFeedbackDTO.getOrderId()) != null) {
                return new ReturnVO(ReturnCode.FAIL, "该产品已经被评价过了");
            }

            if (orderDTO.getPhase() == 0) {
                return new ReturnVO(ReturnCode.FAIL, "订单未完成不可以评价");
            }
        }

        if (userMapper.queryUserId(Integer.parseInt(addFeedbackDTO.getUserId()), 1) == null &&
                userMapper.queryUserId(Integer.parseInt(addFeedbackDTO.getUserId()), 2) == null) {
            return new ReturnVO(ReturnCode.FAIL, "用户不存在");
        }

        Integer num = feedbackMapper.addFeedback(Integer.parseInt(addFeedbackDTO.getUserId()), addFeedbackDTO.getOrderId(), addFeedbackDTO.getInformation(), 0, Tools.getDate(), Tools.getDate());

        if (num == null) {
            return new ReturnVO(ReturnCode.FAIL, "插入失败");
        }

        return new ReturnVO();
    }

    @Override
    public ReturnVO queryAllFeedback(QueryAllMarkerFeedbackDTO queryAllMarkerFeedbackDTO) {
        List<QueryAllFeedbackDTO> list = new ArrayList<>();

        for (FeedbackBean feedbackBean : feedbackMapper.queryAllFeedback(queryAllMarkerFeedbackDTO.getMarker())) {
            UserBean userBean = userMapper.queryUserId(feedbackBean.getUser_id(), queryAllMarkerFeedbackDTO.getMarker());
            if (userBean == null) {
                continue;
            }
            list.add(new QueryAllFeedbackDTO(
                    feedbackBean.getUser_id(),
                    feedbackBean.getOrder_id() != 0 ? productMapper.queryProduct(orderMapper.queryOrder(feedbackBean.getOrder_id()).getProduct_id()) : null,
                    feedbackBean.getInformation(),
                    feedbackBean.getTradition() == 0 ? "完成" : "没有完成",
                    userBean.getName(),
                    userBean.getDepartment(),
                    feedbackBean.getOrder_id() == 0 ? "系统反馈" : "产品反馈"
            ));
        }

        return new ReturnVO(list);
    }

    @Override
    public ReturnVO updateFeedback(UpdateFeedbackDTO updateFeedbackDTO) {
        Integer num = feedbackMapper.updateFeedback(updateFeedbackDTO.getFeedbackId());

        if (num == null) {
            return new ReturnVO(ReturnCode.FAIL, "更新失败");
        }

        return new ReturnVO();
    }
}
