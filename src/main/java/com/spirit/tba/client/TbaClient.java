package com.spirit.tba.client;

/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
import com.spirit.tba.core.TbaEvent;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import java.net.InetSocketAddress;

public class TbaClient<T> {

    private T session;
    private Channel channel = null;
    //private Long serverRandom = null;
    private ByteToMessageDecoder decoder = null;
    private MessageToByteEncoder<Object> encoder = null;
    private SimpleChannelInboundHandler eventHandler = null;

    public void config(ByteToMessageDecoder decoder, MessageToByteEncoder<Object> encoder, SimpleChannelInboundHandler eventHandler) {
        this.decoder = decoder;
        this.encoder = encoder;
        this.eventHandler = eventHandler;
    }

    public void connect(String host, Integer port) throws Exception {

        NioEventLoopGroup group = new NioEventLoopGroup();

        new Bootstrap().channel(NioSocketChannel.class)
                .group(group)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new IdleStateHandler(0, 30, 0));
                        pipeline.addLast("decode", decoder);
                        pipeline.addLast("encode", encoder);
                        pipeline.addLast(eventHandler);
                    }
                })
                .connect(new InetSocketAddress(host, port))
                .addListener((ChannelFutureListener) future -> {
                    if (future.isSuccess()) {
                        channel = (SocketChannel) future.channel();
                        //SRpcBizApp.getInstance().setState(State.LOGIN_SERVER_CONNECT);
                    } else {
                        future.channel().close();
                        group.shutdownGracefully();
                        //SRpcBizApp.getInstance().setState(State.LOGIN_SERVER_DISCONNECT);
                    }
                });
    }

    public void close() {
        channel.close();
        //SRpcBizApp.getInstance().setState(State.LOGIN_SERVER_DISCONNECT);
    }

    public Channel getChannel() {
        return channel;
    }

    public void putRelayEvent(TbaEvent ev) {
        channel.writeAndFlush(ev);
    }


}
