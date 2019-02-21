package com.zzq.config.rabbit;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.context.annotation.Configuration;

/**
 * 消息接收者，消费者
 */
@Configuration
// 消息监听
@RabbitListener(
        // 绑定队列
        bindings = {
                // 绑定队列的类
                @QueueBinding(
                        // 1.队列名称   2.自动删除，配不配无所谓
                        value = @Queue(value = "${mq.config.queue.info}",autoDelete = "true"),
                        // 消息交换器 1.交换器名称，2.交换器类型
                        exchange = @Exchange(value = "${mq.config.exchange}" , type = ExchangeTypes.DIRECT),
                        // 消息路由键
                        key = "${mq.config.queue.info.routing.key}"
                )
        }
)
public class InfoConsumer {

    /**
     * 接收消息的处理方法,带上 @RabbitHandler 表示该队列监听到的消息由该方法处理
     * @param msg
     */
    @RabbitHandler
    public void infoReceiver(String msg){
        System.out.println( "【INFO】消息接收成功,消息是：【"+ msg +"】" );
    }

}
