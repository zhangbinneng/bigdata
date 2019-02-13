package org.qianfeng.bigdata.kafka.kafkaCode;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.qianfeng.bigdata.kafka.kafkaCode.kafkautil.GetKafkaUtil;

import java.util.Properties;

/**
 * @description
 * @author: 张斌能
 * @create: 2019-01-02 19:38:21
 **/

public class MyKafkaProducer {
    public static void main(String[] args) {
        Properties kafkaProducerProperties = new Properties();
        kafkaProducerProperties.put("bootstrap.servers", "zk1:9092");
        kafkaProducerProperties.put("acks", "all");
        kafkaProducerProperties.put("retries", 0);
        kafkaProducerProperties.put("batch.size", 16384);
        kafkaProducerProperties.put("linger.ms", 1);
        kafkaProducerProperties.put("buffer.memory", 33554432);
        kafkaProducerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String,String> producer = null;
        try{
            producer = new KafkaProducer(kafkaProducerProperties);
            int currentConsumerCount = 0;
            for (int i = 0;i < 10000; i++) {
                producer.send(new ProducerRecord("test","我是你爸爸"));
                System.out.println(i+"\t"+"\tSent"+"我是你爸爸" + ++currentConsumerCount + "条");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
