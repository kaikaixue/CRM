package com.controller;

import com.dto.AddFeedbackDTO;
import com.dto.FeedBackDTO;

import com.dto.QueryAllMarkerFeedbackDTO;
import com.dto.UpdateFeedbackDTO;
import com.service.FeedbackService;
import com.vo.ReturnVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Fall
 */
@RestController
@Api(tags = "反馈接口")
public class FeedbackController {
    @Resource
    private FeedbackService feedbackService;

    @RequestMapping(value = "/addFeedback")
    @ApiOperation(value = "增加反馈接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO addFeedback(@RequestBody @Validated AddFeedbackDTO addFeedbackDTO) {
        return feedbackService.addFeedback(addFeedbackDTO);
    }

    @RequestMapping(value = "/queryAllFeedback")
    @ApiOperation(value = "返回所有反馈接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryAllFeedback(@RequestBody @Validated QueryAllMarkerFeedbackDTO queryAllMarkerFeedbackDTO) {
        return feedbackService.queryAllFeedback(queryAllMarkerFeedbackDTO);
    }

    @RequestMapping(value = "/updateFeedback")
    @ApiOperation(value = "更新反馈接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO updateFeedback(@RequestBody @Validated UpdateFeedbackDTO updateFeedbackDTO) {
        return feedbackService.updateFeedback(updateFeedbackDTO);
    }
}
