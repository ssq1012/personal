package com.example.demo.pojo;

/**
 *
 * @author ssq
 * @date 2022/05/07 14:37
 */
public class CheckException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_ERR_CODE = "400";
    public CheckException(String errMessage) {
        super(errMessage);
    }
}
