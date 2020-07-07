package com.example.kafkacomsumer.controller;

import com.example.kafkacomsumer.Kafka_Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class KafkaController {
    @Autowired
    Kafka_Config kafka_config;
    private static final String MY_TOPIC = Kafka_Config.topic;
    @RequestMapping("/getMessage")
    public void getMessage(){
        //线程数设置成分区数，最大化提高消费能力
        int consumerThreadNum = 10;
        for (int i = 0; i < consumerThreadNum; i++) {
            new KafkaConsumerThread(kafka_config.customerConfigs(), MY_TOPIC).start();
        }
    }

}
