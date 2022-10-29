package com.example.rabbitmqdemo.demo4_ack;

import com.example.rabbitmqdemo.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

import java.util.Scanner;
import java.util.UUID;

/**
 * 任务
 *
 * @author WTDYang
 * @date 2022/10/29
 */
public class Task2 {
    public static final int MESSAGE_COUNT = 10000;
    public static void main(String[] argv) throws Exception {
        try (Channel channel = RabbitMQUtil.getChannel())
        {
            String queueName = UUID.randomUUID().toString();
            channel.queueDeclare(queueName, false, false, false, null);
            //开启发布确认
            channel.confirmSelect();
            long begin = System.currentTimeMillis();
            for (int i = 0; i < MESSAGE_COUNT; i++)
            {String message = i + "";
                channel.basicPublish("", queueName, null, message.getBytes());
            //服务端返回 false 或超时时间内未返回，生产者可以消息重发
                boolean flag = channel.waitForConfirms();
                if(flag){
                    System.out.println("消息发送成功");
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("发布" + MESSAGE_COUNT + "个单独确认消息,耗时" + (end - begin) +"ms");
        }
}}