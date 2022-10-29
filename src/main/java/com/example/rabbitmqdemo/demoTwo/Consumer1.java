package com.example.rabbitmqdemo.demoTwo;

import com.example.rabbitmqdemo.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;


/**
 * consumer1
 *
 * @author WTDYang
 * @date 2022/10/29
 */
public class Consumer1 {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtil.getChannel();

        //接收消息
        System.out.println("C1 消费者启动等待消费.................. ");
        channel.basicConsume(QUEUE_NAME,true,
        (consumerTag,delivery)->{
            String receivedMessage = new String(delivery.getBody());
            System.out.println("C1接收到消息:"+receivedMessage);
        },
        (consumerTag)->{
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        }
        );
    }
}
