package org.example.api;

import io.netty.channel.Channel;
import org.example.dto.ChannelGroupNameDto;
import org.example.dto.ServiceEntry;
import org.example.dto.consumer.ConsumerSubscribeReq;
import org.example.dto.consumer.ConsumerUnSubscribeReq;
import org.example.dto.req.MqMessage;
import org.example.dto.resp.MqCommonResp;

import java.util.List;

/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 13:55
 */
public interface IBrokerConsumerService {
    MqCommonResp register(final ServiceEntry serviceEntry, Channel channel);

    MqCommonResp unRegister(final ServiceEntry serviceEntry, Channel channel);

    MqCommonResp subscribe(final ConsumerSubscribeReq serviceEntry,
                           final Channel clientChannel);

    MqCommonResp unSubscribe(final ConsumerUnSubscribeReq serviceEntry,
                             final Channel clientChannel);

    List<ChannelGroupNameDto> getPushSubscribeList(MqMessage mqMessage);


}
