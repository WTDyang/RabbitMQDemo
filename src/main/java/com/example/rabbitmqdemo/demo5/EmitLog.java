package com.example.rabbitmqdemo.demo5;

import com.example.rabbitmqdemo.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

public class EmitLog {
    private static String EXCHANGE_NAME = "loggers";
    public static void main(String[] args) throws Exception {
        try (Channel channel = RabbitMQUtil.getChannel()) {
        /**
         * 声明一个 exchange
         * 1.exchange 的名称
         * 2.exchange 的类型
         */
         //   channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入信息");
            while (sc.hasNext()) {
                String message = sc.nextLine();
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
                System.out.println("生产者发出消息" + message);
            }
        }
    }
}
