package com.example.nettymodel.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * @author 厚积薄发
 * @create 2023-05-06-15:34
 */
public class NettyServerHandle extends ChannelInboundHandlerAdapter {
    /**
     * 读取数据
     *
     * @param: 1.ChannelHandlerContext ctx:上下文对象, 含有 管道 pipeline , 通道 channel, 地址
     * @param: 2. Object msg: 就是客户端发送的数据 默认 Object
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.err.println("服务器读取线程 " + Thread.currentThread().getName());
        System.out.println("server ctx =" + ctx);
        System.out.println("看看 channel 和 pipeline 的关系");

        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline(); //本质是一个双向链接, 出站入站

        //将 msg 转成一个 ByteBuf,ByteBuf 是 Netty 提供的，不是 NIO 的 ByteBuffer.
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送消息是:" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址:" + channel.remoteAddress());
    }

    /**
     * 读取数据完成后
     *
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //writeAndFlush 是 write + flush
        //将数据写入到缓存，并刷新
        //一般讲，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵", CharsetUtil.UTF_8));
    }

    //处理异常, 一般是需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
