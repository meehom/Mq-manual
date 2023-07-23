package org.example.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import org.example.api.IBrokerConsumerService;
import org.example.api.IBrokerProducerService;
import org.example.api.IMqBroker;
import org.example.consant.BrokerConst;
import org.example.support.api.LocalBrokerConsumerService;
import org.example.support.api.LocalBrokerProducerService;
import org.example.utils.DelimiterUtil;
import sun.rmi.runtime.Log;

/**
 * @version 1.0
 * @Author meehom
 * @Date 2023/7/23 13:50
 */
public class MqBroker extends Thread implements IMqBroker {

    private int port = BrokerConst.DEFAULT_PORT;

    /**
     * 消费者管理
     *
     * @since 0.0.3
     */
    private IBrokerConsumerService registerConsumerService = new LocalBrokerConsumerService();

    /**
     * 生产者管理
     *
     * @since 0.0.3
     */
    private IBrokerProducerService registerProducerService = new LocalBrokerProducerService();


    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(workerGroup, bossGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline();

                    }
                })
                // 这个参数影响的是还没有被accept 取出的连接
                .option(ChannelOption.SO_BACKLOG, 128)
                // 这个参数只是过一段时间内客户端没有响应，服务端会发送一个 ack 包，以判断客户端是否还活着。
                .childOption(ChannelOption.SO_KEEPALIVE, true);
    }
}
