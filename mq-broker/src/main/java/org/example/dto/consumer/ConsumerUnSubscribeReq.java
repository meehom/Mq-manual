package org.example.dto.consumer;

import lombok.Data;
import org.example.dto.req.MqCommonReq;

/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 15:11
 */
@Data
public class ConsumerUnSubscribeReq extends MqCommonReq {
    /**
     * 分组名称
     * @since 0.0.3
     */
    private String groupName;

    /**
     * 标题名称
     */
    private String topicName;

    /**
     * 标签正则
     */
    private String tagRegex;

    /**
     * 消费者类型
     * @since 0.0.9
     */
    private String consumerType;

}
