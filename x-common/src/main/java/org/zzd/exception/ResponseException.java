package org.zzd.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zzd.result.ResultCodeEnum;

/**
 * @author :zzd
 * @date : 2022/12/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "错误信息")
    private String message;

    public ResponseException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public ResponseException(String message) {
        this.code = 500;
        this.message = message;
    }

    public ResponseException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
