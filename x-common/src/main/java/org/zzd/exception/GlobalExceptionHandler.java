package org.zzd.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zzd.result.ResponseResult;
import org.zzd.result.ResultCodeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @apiNote 全局异常处理类
 * @author zzd
 * @date 2023-03-01 16:11
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
        return ResponseResult.error(ex.getCode(), ex.getMessage());
    }

    /**
     * @apiNote 参数校验异常
     * @param e e
     * @return {@link ResponseResult }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult error(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: ", e);
        return handleBindingResult(e.getBindingResult());
    }

    private ResponseResult handleBindingResult(BindingResult result) {
        //把异常处理为对外暴露的提示
        List<String> list = new ArrayList<>();
        //是不是包含错误
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError objectError : allErrors) {
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }
        if (list.size() == 0) {
            return ResponseResult.error(ResultCodeEnum.PARAM_NOT_COMPLETE);
        }
        return ResponseResult.error(ResultCodeEnum.PARAM_NOT_COMPLETE.getCode(), list.toString());
    }
}
