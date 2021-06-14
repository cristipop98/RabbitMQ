package com.rabbitmq.demo.controller;


import com.rabbitmq.demo.producer.Activity;
import com.rabbitmq.demo.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RabbitController {

    @Autowired
    Producer producer;

    @GetMapping("/send")
    public String sendMessage() {
       // System.out.println("*****" + activities
       // );
       // for (int i = 0; i < 25; i++) {
            producer.produceMsg();

        //}
        return "Succesfully Msg Sent";
    }
}

