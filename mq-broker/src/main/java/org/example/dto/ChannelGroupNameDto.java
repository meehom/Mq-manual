package org.example.dto;

import io.netty.channel.Channel;
import lombok.Data;

/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 15:15
 */
@Data
public class ChannelGroupNameDto {
    /**
     * 分组名称
     */
    private String consumerGroupName;

    /**
     * 通道
     */
    private Channel channel;

    public static ChannelGroupNameDto of(String consumerGroupName,
                                         Channel channel) {
        ChannelGroupNameDto dto = new ChannelGroupNameDto();
        dto.setConsumerGroupName(consumerGroupName);
        dto.setChannel(channel);
        return dto;
    }
}
