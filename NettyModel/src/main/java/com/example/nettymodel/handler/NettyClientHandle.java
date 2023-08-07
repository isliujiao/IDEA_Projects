package com.example.nettymodel.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author 厚积薄发
 * @create 2023-05-06-15:35
 */
public class NettyClientHandle extends ChannelInboundHandlerAdapter {

    //如果client 端服务启动完成后
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.err.println("client "+ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,netty server...",CharsetUtil.UTF_8));
    }

    //当通道有读事件时
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;
        System.err.println("服务器端回复消息："+byteBuf.toString(CharsetUtil.UTF_8));
        System.err.println("服务器端地址是："+ctx.channel().remoteAddress());
    }

    //当通道有异常时

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}