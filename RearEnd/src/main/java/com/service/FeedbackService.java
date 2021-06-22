package com.service;


import com.dto.AddFeedbackDTO;
import com.dto.FeedBackDTO;
import com.dto.QueryAllMarkerFeedbackDTO;
import com.dto.UpdateFeedbackDTO;
import com.vo.ReturnVO;

/**
 * @author Fall
 */
public interface FeedbackService {
    /**
     * 增加反馈
     * @param addFeedbackDTO AddFeedbackDTO
     * @return ReturnVO
     */
    ReturnVO addFeedback(AddFeedbackDTO addFeedbackDTO);

    /**
     * 查询所有的反馈
     * @param queryAllMarkerFeedbackDTO QueryAllMarkerFeedbackDTO
     * @return ReturnVO
     */
    ReturnVO queryAllFeedback(QueryAllMarkerFeedbackDTO queryAllMarkerFeedbackDTO);

    /**
     * 更新反馈
     * @param updateFeedbackDTO UpdateFeedbackDTO
     * @return ReturnVO
     */
    ReturnVO updateFeedback(UpdateFeedbackDTO updateFeedbackDTO);
}
