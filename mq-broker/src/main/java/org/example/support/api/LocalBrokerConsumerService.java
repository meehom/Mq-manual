package org.example.support.api;

import io.netty.channel.Channel;
import org.example.api.IBrokerConsumerService;
import org.example.dto.BrokerServiceEntryChannel;
import org.example.dto.ChannelGroupNameDto;
import org.example.dto.ServiceEntry;
import org.example.dto.consumer.ConsumerSubscribeBo;
import org.example.dto.consumer.ConsumerSubscribeReq;
import org.example.dto.consumer.ConsumerUnSubscribeReq;
import org.example.dto.req.MqMessage;
import org.example.dto.resp.MqCommonResp;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 15:20
 */
public class LocalBrokerConsumerService implements IBrokerConsumerService {
    private final Map<String, BrokerServiceEntryChannel> registerMap = new ConcurrentHashMap<>();

    private final Map<String, Set<ConsumerSubscribeBo>> pushSubscribeMap = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public LocalBrokerConsumerService() {
        //120S 扫描一次
        final long limitMills = 2 * 60 * 1000;
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                for(Map.Entry<String, BrokerServiceEntryChannel> entry : registerMap.entrySet()) {
                    String key  = entry.getKey();
                    long lastAccessTime = entry.getValue().getLastAccessTime();
                    long currentTime = System.currentTimeMillis();

                    if(currentTime - lastAccessTime > limitMills) {
                        removeByChannelId(key);
                    }
                }
            }
        }, 2 * 60, 2 * 60, TimeUnit.SECONDS);
    }

    private void removeByChannelId(final String channelId) {
        BrokerServiceEntryChannel channelRegister = registerMap.remove(channelId);
    }

    @Override
    public MqCommonResp register(ServiceEntry serviceEntry, Channel channel) {
        final String channelId = channel.id().asLongText();
        BrokerServiceEntryChannel result = new BrokerServiceEntryChannel();
        result.setChannel(channel);
        result.setGroupName(serviceEntry.getGroupName());
        result.setAddress(serviceEntry.getAddress());
        result.setPort(serviceEntry.getPort());
        registerMap.put(channelId, result);
        MqCommonResp resp = new MqCommonResp();
        resp.setRespCode("0000");
        resp.setRespMessage("成功");
        return resp;
    }

    @Override
    public MqCommonResp unRegister(ServiceEntry serviceEntry, Channel channel) {
        final String channelId = channel.id().asLongText();
        removeByChannelId(channelId);
        MqCommonResp resp = new MqCommonResp();
        resp.setRespCode("0000");
        resp.setRespMessage("成功");
        return resp;
    }

    @Override
    public MqCommonResp subscribe(ConsumerSubscribeReq serviceEntry, Channel clientChannel) {
        String topicName = serviceEntry.getTopicName();
        final String channelId = clientChannel.id().asLongText();
        ConsumerSubscribeBo subscribeBo = new ConsumerSubscribeBo();
        subscribeBo.setChannelId(channelId);
        subscribeBo.setGroupName(serviceEntry.getGroupName());
        subscribeBo.setTopicName(topicName);
        subscribeBo.setTagRegex(serviceEntry.getTagRegex());
        Set<ConsumerSubscribeBo> consumerSubscribeBos = pushSubscribeMap.get(topicName);
        consumerSubscribeBos.add(subscribeBo);
        pushSubscribeMap.put(topicName, consumerSubscribeBos);
        MqCommonResp resp = new MqCommonResp();
        resp.setRespCode("0000");
        resp.setRespMessage("成功");
        return resp;
    }

    @Override
    public MqCommonResp unSubscribe(ConsumerUnSubscribeReq serviceEntry, Channel clientChannel) {
        String topicName = serviceEntry.getTopicName();
        final String channelId = clientChannel.id().asLongText();
        ConsumerSubscribeBo subscribeBo = new ConsumerSubscribeBo();
        subscribeBo.setChannelId(channelId);
        subscribeBo.setGroupName(serviceEntry.getGroupName());
        subscribeBo.setTopicName(topicName);
        subscribeBo.setTagRegex(serviceEntry.getTagRegex());
        Set<ConsumerSubscribeBo> consumerSubscribeBos = pushSubscribeMap.get(topicName);
        consumerSubscribeBos.remove(subscribeBo);
        pushSubscribeMap.put(topicName, consumerSubscribeBos);
        MqCommonResp resp = new MqCommonResp();
        resp.setRespCode("0000");
        resp.setRespMessage("成功");
        return resp;
    }

    @Override
    public List<ChannelGroupNameDto> getPushSubscribeList(MqMessage mqMessage) {
        final String topicName = mqMessage.getTopic();
        String groupName = mqMessage.getGroupName();
        Set<ConsumerSubscribeBo> set = pushSubscribeMap.get(topicName);
        List<ChannelGroupNameDto> channelGroupNameList = new ArrayList<>();
        for (ConsumerSubscribeBo bo : set){
            String channelId = bo.getChannelId();
            ChannelGroupNameDto channelGroupNameDto = ChannelGroupNameDto.of(groupName,
                    registerMap.get(channelId).getChannel());
            channelGroupNameList.add(channelGroupNameDto);

        }
        return channelGroupNameList;
    }
}
