package org.example.dto.req;

import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 15:14
 */
@Data
public class MqMessage extends MqCommonReq {
    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 标题名称
     */
    private String topic;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 内容
     */
    private String payload;

    /**
     * 业务标识
     */
    private String bizKey;

    /**
     * 负载分片标识
     */
    private String shardingKey;
}
