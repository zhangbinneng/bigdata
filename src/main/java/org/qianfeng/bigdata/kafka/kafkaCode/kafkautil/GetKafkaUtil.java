package org.qianfeng.bigdata.kafka.kafkaCode.kafkautil;

import java.util.Properties;

/**
 * @description
 * @author: 张斌能
 * @create: 2019-01-02 19:39:42
 **/

public class GetKafkaUtil {
    public static  Properties getKafkaConsumerProperties(){
        Properties kafkaConsumerProperties = new Properties();
        kafkaConsumerProperties.put("bootstrap.servers","zk1:9092");
        kafkaConsumerProperties.put("group.id","test111");
        kafkaConsumerProperties.put("enable.auto.commit","true");
        kafkaConsumerProperties.put("auto.commit.interval.ms","1000");
        kafkaConsumerProperties.put("auto.offset.reset","earliest");
        kafkaConsumerProperties.put("session.timeout.ms","30000");
        kafkaConsumerProperties.put("key.deserializer","org.apache.common.serialization.StringDeserializer");
        kafkaConsumerProperties.put("value.deserializer","org.apache.common.serialization.StringDeserializer");
        return kafkaConsumerProperties;
    }
    public static Properties getKafkaProducerProperties(){
        Properties kafkaProducerProperties = new Properties();
        kafkaProducerProperties.put("bootstrap.servers", "zk1:9092");
        kafkaProducerProperties.put("acks", "all");
        kafkaProducerProperties.put("retries", 0);
        kafkaProducerProperties.put("batch.size", 16384);
        kafkaProducerProperties.put("linger.ms", 1);
        kafkaProducerProperties.put("buffer.memory", 33554432);
        kafkaProducerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return kafkaProducerProperties;
    }
}
