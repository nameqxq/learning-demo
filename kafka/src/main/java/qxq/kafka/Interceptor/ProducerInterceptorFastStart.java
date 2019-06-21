package qxq.kafka.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/6/1 10:51
 **/
@Slf4j
public class ProducerInterceptorFastStart implements ProducerInterceptor<String, String> {
    private volatile static AtomicInteger NUM = new AtomicInteger(0);
    private int count = NUM.addAndGet(1);
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return new ProducerRecord<>(record.topic(), record.value() + " --> append in interceptor" + count);
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        log.info("in interceptor call back ！  {}", metadata.toString());
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
