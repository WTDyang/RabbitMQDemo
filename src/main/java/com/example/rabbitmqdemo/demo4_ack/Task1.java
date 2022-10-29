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
public class Task1 {
    public static final int MESSAGE_COUNT = 10000;
    public static void main(String[] argv) throws Exception {
        try (Channel channel = RabbitMQUtil.getChannel())
        {String queueName = UUID.randomUUID().toString();
            channel.queueDeclare(queueName, false, false, false, null);
        //开启发布确认
            channel.confirmSelect();
        //批量确认消息大小
            int batchSize = 100;
        //未确认消息个数
            int outstandingMessageCount = 0;
            long begin = System.currentTimeMillis();
            for (int i = 0; i < MESSAGE_COUNT; i++)
            {String message = i + "";
                channel.basicPublish("", queueName, null, message.getBytes());
                System.out.println("消息发送");
                outstandingMessageCount++;
                if (outstandingMessageCount == batchSize)
                {channel.waitForConfirms();
                    System.out.println("------发送成功-----------");
                    outstandingMessageCount = 0;
                }
            }
        //为了确保还有剩余没有确认消息 再次确认
            if (outstandingMessageCount > 0)
            {channel.waitForConfirms();
            }
            long end = System.currentTimeMillis();
            System.out.println("发布" + MESSAGE_COUNT + "个批量确认消息,耗时" + (end - begin) +"ms");
        }
    }
}