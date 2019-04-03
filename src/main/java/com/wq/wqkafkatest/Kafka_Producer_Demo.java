package com.wq.wqkafkatest;

import kafka.producer.KeyedMessage;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

import java.util.Properties;

///////测试通过
public class Kafka_Producer_Demo {

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.put("metadata.broker.list", "kafka1:9092,kafka2:9092,kafka3:9092");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
//        properties.put("partitioner.class","cn.com.jinghang.MyPartitioner");
        ProducerConfig config = new ProducerConfig(properties);
        Producer<String, String> producer = new Producer<String, String>(config);
//        Producer<String,String> producer = new Producer<String,String>(config);
        int i = 0;
        while (true) {
            i += 1;
            KeyedMessage<String, String> keyedMessage = new KeyedMessage<String, String>("wuqing1",""+i, "wuqinghaha---"+i);
            producer.send(keyedMessage);
            System.out.println("发送成功了" + i);
            Thread.sleep(3000);
        }
    }
}
