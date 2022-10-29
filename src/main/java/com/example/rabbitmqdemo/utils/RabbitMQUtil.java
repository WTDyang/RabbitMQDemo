package com.example.rabbitmqdemo.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Rabbitmqutil
 *
 * @author WTDYang
 * @date 2022/10/29
 */
public class RabbitMQUtil {
    //得到一个连接的 channel
    public static Channel getChannel() throws Exception{
    //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("49.232.150.250");
        factory.setUsername("huihe");
        factory.setPassword("123456");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        return channel;
    }
}
