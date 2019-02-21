package com.zzq.config.controller;

import com.zzq.config.rabbitmq.ErrorSender;
import com.zzq.config.rabbitmq.InfoSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RabbitController {

    @Autowired
    private InfoSender infoSender;

    @Autowired
    private ErrorSender errorSender;

    @RequestMapping("/rabbit")
    public String send(){
        while(true){
            try{
                Thread.sleep(1000*10);
            }catch (Exception e){
                e.printStackTrace();
            }
            this.errorSender.send(new Date()+"<===>"+Math.random()*10000 +"" );
            this.infoSender.send(new Date()+"<===>"+Math.random()*10000 +"" );
        }
    }

}
