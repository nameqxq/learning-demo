package qxq.kafka.product;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/6/1 10:00
 **/
public class MainProducer {

    public static void main(String[] args) {
        Producer<String> producer = new ProducerFastStart();
        // Producer producer = new UserProducer();
        producer.send();
    }
}
