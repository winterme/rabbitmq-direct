package com.zzq.config.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfoSender {

    private final static Logger log = LoggerFactory.getLogger(InfoSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    // 交换器名称
    @Value("${mq.config.exchange}")
    private String exchange;

    // 路由键名称
    @Value("${mq.config.queue.info.routing.key}")
    private String routingkey;

    // 发送消息的方法
    public void send(String msg){
        // 三个参数
        /**
         * 1.交换器的名称
         * 2.路由键的名称
         * 3.消息
         */
        this.amqpTemplate.convertAndSend(this.exchange, this.routingkey, msg);
        log.info("【INFO】消息发送成功！exchange：【{}】,routingkey:【{}】",this.exchange,this.routingkey);
    }

}
