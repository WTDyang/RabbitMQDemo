package com.example.rabbitmqdemo.demo6;

import com.example.rabbitmqdemo.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

public class EmitLog {
    private static String EXCHANGE_NAME = "logs";
    public static void main(String[] args) throws Exception {
        try (Channel channel = RabbitMQUtil.getChannel()) {
        /**
         * 声明一个 exchange
         * 1.exchange 的名称
         * 2.exchange 的类型
         */
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            Scanner sc = new Scanner(System.in);
            System.out.println("生产者启动");
            while (true) {
                System.out.println("请输入信息");
                String message = sc.nextLine();
                System.out.println("请输入routing key");
                String key = sc.nextLine();
                channel.basicPublish(EXCHANGE_NAME, key, null, message.getBytes("UTF-8"));
                System.out.println("生产者发出消息" + message);
            }
        }
    }
}
