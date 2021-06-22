package com.mapper;

import com.bean.FeedbackBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Fall
 */
@Mapper
public interface FeedbackMapper {
    /**
     * 增加反馈
     * @param userId Integer
     * @param orderId Integer
     * @param information String
     * @param tradition Integer
     * @param createTime Long
     * @param updateTime Long
     * @return Integer
     */
    Integer addFeedback(Integer userId, Integer orderId, String information, Integer tradition, Long createTime, Long updateTime);

    /**
     * 查询所有的反馈
     * @param marker Integer
     * @return List<FeedbackBean>
     */
    List<FeedbackBean> queryAllFeedback(Integer marker);

    /**
     * 更新反馈
     * @param id Integer
     * @return Integer
     */
    Integer updateFeedback(Integer id);

    /**
     * 查询订单是否被反馈过
     * @param orderId Integer
     * @return Feedback
     */
    FeedbackBean queryOrderFeedback(Integer orderId);
}
