package qxq.kafka.product;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import qxq.kafka.common.Config;
import qxq.kafka.module.User;
import qxq.kafka.serializer.UserSerializer;

import java.util.Properties;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/6/1 9:56
 **/
public class UserProducer implements Producer{
    @Override
    public void initConfig(Properties props) {
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                UserSerializer.class.getName());
    }

    @Override
    public ProducerRecord buildRecord() {
        User user = new User("quxiqi", 23);
        return new ProducerRecord<>(Config.TOPIC, user);
    }
}
