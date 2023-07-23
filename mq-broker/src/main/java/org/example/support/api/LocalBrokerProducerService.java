package org.example.support.api;

import io.netty.channel.Channel;
import org.example.api.IBrokerProducerService;
import org.example.dto.BrokerServiceEntryChannel;
import org.example.dto.ServiceEntry;
import org.example.dto.resp.MqCommonResp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 15:21
 */
public class LocalBrokerProducerService implements IBrokerProducerService {

    @Override
    public MqCommonResp register(ServiceEntry serviceEntry, Channel channel) {
        return null;
    }

    @Override
    public MqCommonResp unRegister(ServiceEntry serviceEntry, Channel channel) {
        return null;
    }

    @Override
    public ServiceEntry getServiceEntry(String channelId) {
        return null;
    }
}
