package com.example.demo.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NioServer {

     static List<SocketChannel> channelList = new ArrayList<SocketChannel>();

    public static void main(String[] args) throws Exception{
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(9000));
        channel.configureBlocking(false);
        while (true){
            SocketChannel accept = channel.accept();
            if (null != accept){
                channel.configureBlocking(false);
                channelList.add(accept);
            }

            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()){
                SocketChannel next = iterator.next();
                ByteBuffer buffer = ByteBuffer.allocate(128);
                int read = next.read(buffer);
                if (read > 0){
                    System.out.println("收到消息：" + new String(buffer.array()));
                }else if (read == -1){
                    iterator.remove();
                    System.out.println("无数据");
                }
            }
        }

    }
}
