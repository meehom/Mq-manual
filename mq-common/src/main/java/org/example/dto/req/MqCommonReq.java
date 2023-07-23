package org.example.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 15:09
 */
@Data
public class MqCommonReq implements Serializable {
    /**
     * 请求标识
     */
    private String traceId;

    /**
     * 方法类型
     */
    private String methodType;
}
