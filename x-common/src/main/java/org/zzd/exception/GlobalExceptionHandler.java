package org.zzd.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zzd.result.ResponseResult;
import org.zzd.result.ResultCodeEnum;

/**
 * @author :zzd
 * @apiNote :全局异常处理类
 * @date : 2023-03-01 16:11
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @param ex: 异常
     * @apiNote 全局异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult error(Exception ex) throws Exception {
        ex.printStackTrace();
        //针对于捕捉不到AccessDeniedHandler的情况，直接向上抛出
        if ("不允许访问".equals(ex.getMessage())) {
            throw ex;
        }
        return ResponseResult.error();
    }

    /**
     * @param ex: 异常
     * @apiNote 指定异常
     */
    @ExceptionHandler(ResponseException.class)
    @ResponseBody
    public ResponseResult error(ResponseException ex) {
        ex.printStackTrace();
        return ResponseResult.error(ex.getCode(),ex.getMessage());
    }

}
