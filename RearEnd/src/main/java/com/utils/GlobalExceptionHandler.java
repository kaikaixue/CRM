package com.utils;

import com.vo.ReturnVO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fall
 */
public class GlobalExceptionHandler {

    /**
     * 自定义异常APIException
     */
    @ExceptionHandler(APIException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ReturnVO APIExceptionHandler(APIException e) {
        return  new ReturnVO(ReturnCode.FAIL, e.getMessage());
    }

    /**
     * 方法参数错误异常
     * @param e
     * @return ReturnVO
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ReturnVO MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<String> list=new ArrayList<>();
        // 从异常对象中拿到ObjectError对象
        if (!e.getBindingResult().getAllErrors().isEmpty()){
            for(ObjectError error:e.getBindingResult().getAllErrors()){
                list.add(error.getDefaultMessage().toString());
            }
        }
        // 然后提取错误提示信息进行返回
        return new ReturnVO(ReturnCode.FAIL, "参数校验失败", list);
    }
}