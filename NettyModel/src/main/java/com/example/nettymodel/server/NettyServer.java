package com.example.nettymodel.server;

import com.example.nettymodel.handler.NettyServerHandle;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class NettyServer {
    public static void main(String[] args) throws Exception {

        /**
         * 1.创建BossGroup 和 WorkerGroup
         *    1.1 创建2个线程组
         *      bossGroup 只处理连接请求
         *      workerGroup 处理客户端的业务逻辑
         *      2个都是无限循环
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //2.创建服务端的启动对象,可以为服务端启动配置一些服务参数
        ServerBootstrap bootStrap = new ServerBootstrap();

        //2.1使用链式编程来配置服务参数
        bootStrap.group(bossGroup, workerGroup)                          //设置2个线程组
                .channel(NioServerSocketChannel.class)                 //使用NioServerSocketChannel作为服务器的通道
                .option(ChannelOption.SO_BACKLOG, 128)            //设置线程等待的连接个数
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE) //设置保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    //给PipeLine设置处理器
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //通过socketChannel得到pipeLine，然后向pipeLine中添加处理的handle
                        socketChannel.pipeline().addLast(new NettyServerHandle());
                    }
                }); //给workerGroup 的EventLoop对应的管道设置处理器(可以自定义/也可使用netty的)
        System.err.println("server is ready......");

        //启动服务器，并绑定1个端口且同步生成一个ChannelFuture 对象
        ChannelFuture channelFuture = bootStrap.bind(8888).sync();

        //对关闭通道进行监听(netty异步模型)
        //当通道进行关闭时，才会触发这个关闭动作
        channelFuture.channel().closeFuture().sync();

    }


}