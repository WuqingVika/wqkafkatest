package com.wq.wqkafkatest;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/////测试通过
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Kafka_Consumer_Demo {
    private static String topic="wuqing1";

    public static void main(String[] args) {

        //1.配置properties
        Properties properties = new Properties();
        properties.put("zookeeper.connect", "kafka1:2181,kafka2:2181,kafka3:2181");
        properties.put("group.id", "test-consumer-group");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        //2.获取消费者实例
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(topic, 1); //线程数，就是一个消费者的实例
        ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumerConnector.createMessageStreams(map);
        //3.消费数据
        List<KafkaStream<byte[], byte[]>> streamList = messageStreams.get("wuqing1");
        KafkaStream<byte[], byte[]> kafkaStream = streamList.get(0);
        ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();
        while (iterator.hasNext()) {
            byte[] message = iterator.next().message();
            System.out.println(new String(message));
        }
    }
}
