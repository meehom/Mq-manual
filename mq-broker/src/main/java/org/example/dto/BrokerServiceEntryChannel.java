package org.example.dto;

import io.netty.channel.Channel;
import lombok.Data;

/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 15:21
 */
@Data
public class BrokerServiceEntryChannel extends ServiceEntry{

    private Channel channel;

    /**
     * 最后访问时间
     * @since 0.0.6
     */
    private long lastAccessTime;
}
