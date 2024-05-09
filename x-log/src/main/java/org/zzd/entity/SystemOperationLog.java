package org.zzd.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (SystemOperationLog)表实体类
 *
 * @author zzd
 * @since 2023-03-02 15:21:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_system_operation_log")
public class SystemOperationLog implements Serializable {
    @TableId
    @ApiModelProperty(value = "日志主键")
    private Long id;

    @ApiModelProperty(value = "模块标题")
    private String title;

    @ApiModelProperty(value = "业务类型（0其它 1新增 2修改 3删除）")
    private String businessType;

    @ApiModelProperty(value = "方法名称")
    private String method;

    @ApiModelProperty(value = "请求方式")
    private String requestMethod;

    @ApiModelProperty(value = "操作类别（0其它 1后台用户 2手机端用户）")
    private String operatorType;

    @ApiModelProperty(value = "操作人员")
    private String operationName;

    @ApiModelProperty(value = "请求URL")
    private String operationUrl;

    @ApiModelProperty(value = "主机地址)")
    private String operationIp;

    @ApiModelProperty(value = "请求参数)")
    private String operationParam;

    @ApiModelProperty(value = "返回参数")
    private String jsonResult;

    @ApiModelProperty(value = "操作状态（0正常 1异常）")
    private Integer status;

    @ApiModelProperty(value = "错误消息")
    private String errorMsg;

    @ApiModelProperty(value = "操作时间")
    private String operationTime;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "登录地址")
    private String address;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}

