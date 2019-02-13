package org.qianfeng.bigdata.kafka.kafkaCode;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.qianfeng.bigdata.kafka.kafkaCode.kafkautil.GetKafkaUtil;

import java.util.Arrays;
import java.util.Properties;

/**
 * @description
 * @author: 张斌能
 * @create: 2019-01-02 19:23:47
 **/

public class MyKafkaConsumer {
    public static void main(String[] args) {
        Properties kafkaConsumerProperties = new Properties();
        kafkaConsumerProperties.put("bootstrap.servers", "zk1:9092");
        kafkaConsumerProperties.put("group.id", "test11111");
        kafkaConsumerProperties.put("enable.auto.commit", "true");
        kafkaConsumerProperties.put("auto.commit.interval.ms", "1000");
        kafkaConsumerProperties.put("auto.offset.reset", "earliest");
        kafkaConsumerProperties.put("session.timeout.ms", "30000");
        kafkaConsumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //构建根据上述构建的properties对象kafka消费者
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer(kafkaConsumerProperties);
        //消费者订阅名为"test"的topic
        kafkaConsumer.subscribe(Arrays.asList("test"));
        //记录本次消费的总量的计数器
        int currentConsumerCount = 0;
        //使用死循环模拟消费者永不停息的消费者生产的内容
        while (true) {
            ConsumerRecords<String,String> records = kafkaConsumer.poll(1000);
            for (ConsumerRecord<String,String> record: records) {
                System.out.printf("offset = %d, value = %s",record.offset(),record.value() + "\t本次共消费：" + ++currentConsumerCount + "条");
                System.out.println();
            }
        }
    }
}
