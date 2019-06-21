package qxq.kafka.product;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import qxq.kafka.common.Config;

import java.util.Properties;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/6/1 9:42
 **/
public interface Producer<T> {


    void initConfig(Properties prop);

    ProducerRecord<String, T> buildRecord();

    default void send() {
        Properties props = new Properties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BROKER_LIST);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
        initConfig(props);

        KafkaProducer<String, T> producer = new KafkaProducer<>(props);
        ProducerRecord<String, T> record = buildRecord();
        try {
            producer.send(record);
            producer.send(record);
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.close();
    }
}
