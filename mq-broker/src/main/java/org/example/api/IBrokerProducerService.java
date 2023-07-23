package org.example.api;

import org.example.dto.ServiceEntry;
import org.example.dto.resp.MqCommonResp;
import io.netty.channel.Channel;
/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 15:19
 */
public interface IBrokerProducerService {
    MqCommonResp register(final ServiceEntry serviceEntry, Channel channel);

    /**
     * 注销当前服务信息
     * @param serviceEntry 注册当前服务信息
     * @param channel 通道
     * @since 0.0.8
     */
    MqCommonResp unRegister(final ServiceEntry serviceEntry, Channel channel);

    /**
     * 获取服务地址信息
     * @param channelId channel
     * @return 结果
     * @since 0.0.3
     */
    ServiceEntry getServiceEntry(final String channelId);
}
