package com.example.rabbitmqdemo.demo6;

import com.example.rabbitmqdemo.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

public class LogConsumer1 {
    private static String EXCHANGE_NAME = "logs";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtil.getChannel();
        //channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,EXCHANGE_NAME,"001");
        System.out.println("日志一开始工作");
        channel.basicConsume(queue,true,
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
