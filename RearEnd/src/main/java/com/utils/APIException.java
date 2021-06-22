package com.utils;

/**
 * @author Fall
 */
public class APIException extends RuntimeException {

    private String code;
    private String msg;

    public APIException() {
        this(ReturnCode.FAIL);
    }

    public APIException(ReturnCode failed) {
        this.code=failed.val();
        this.msg=failed.msg();
    }
}