package qxq.kafka.product;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import qxq.kafka.Interceptor.ProducerInterceptorFastStart;
import qxq.kafka.common.Config;

import java.util.Properties;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/5/28 16:48
 **/
public class ProducerFastStart implements Producer<String> {

    @Override
    public void initConfig(Properties props){
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
                ProducerInterceptorFastStart.class.getName()+","+ProducerInterceptorFastStart.class.getName());
    }

    @Override
    public ProducerRecord<String, String> buildRecord() {
        return new ProducerRecord<>(Config.TOPIC, "Hello, Kafka! -- from java");
    }

}
