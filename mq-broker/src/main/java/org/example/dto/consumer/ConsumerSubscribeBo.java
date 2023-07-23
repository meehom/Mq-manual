package org.example.dto.consumer;

import lombok.Data;
import org.example.dto.req.MqCommonReq;

/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 15:36
 */
@Data
public class ConsumerSubscribeBo {
    /**
     * address 信息
     * @since 0.0.3
     */
    private String address;

    /**
     * 端口号
     * @since 0.0.3
     */
    private int port;


    /**
     * 分组名称
     * @since 0.0.3
     */
    private String groupName;

    /**
     * 标题名称
     * @since 0.0.3
     */
    private String topicName;

    /**
     * 标签正则
     * @since 0.0.3
     */
    private String tagRegex;

    /**
     * 通道标识
     * @since 0.0.3
     */
    private String channelId;
}
