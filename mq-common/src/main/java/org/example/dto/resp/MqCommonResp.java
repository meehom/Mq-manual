package org.example.dto.resp;


import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 15:05
 */
@Data
public class MqCommonResp implements Serializable {
    /**
     * 响应编码
     * @since 1.0.0
     */
    private String respCode;

    /**
     * 响应消息
     * @since 1.0.0
     */
    private String respMessage;
}
