package com.example.kafkacomsumer.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerThread extends Thread{
    private KafkaConsumer kafkaConsumer;

    public KafkaConsumerThread(Properties props, String topic) {
        this.kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
        this.kafkaConsumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        try {
            while (true) {
                ConsumerRecords<String, String> records =
                        kafkaConsumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(Thread.currentThread().getName()+"message------------ "+record.value());
                    System.out.println("+++++++++++++++++++++++++++++++"+record.value()+"====="+record.key());
                }
            }
        } catch (Exception e) {
        } finally {
            kafkaConsumer.close();
        }
    }
}
