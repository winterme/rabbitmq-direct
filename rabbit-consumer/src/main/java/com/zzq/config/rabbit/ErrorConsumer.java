package com.zzq.config.rabbit;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.context.annotation.Configuration;

/**
 * 消息接收类
 */
@Configuration
@RabbitListener(
        // 队列绑定
        bindings = {
                @QueueBinding(
                        // 绑定队列 1.队列名称，2队列是否自动删除
                        value = @Queue(value = "${mq.config.queue.error}",autoDelete = "true"),
                        // 绑定交换器 1.交换器名称，2交换器类型
                        exchange = @Exchange(value = "${mq.config.exchange}",type = ExchangeTypes.DIRECT),
                        // 绑定 路由键
                        key = "${mq.config.queue.error.routing.key}"
                )
        }
)
public class ErrorConsumer {

    /**
     * 接收消息的处理方法,带上 @RabbitHandler 表示该队列监听到的消息由该方法处理
     * @param msg
     */
    @RabbitHandler
    public void errorReceiver(String msg){
        System.err.println( "【ERROR】消息接收成功,消息是：【"+ msg +"】" );
    }

}
