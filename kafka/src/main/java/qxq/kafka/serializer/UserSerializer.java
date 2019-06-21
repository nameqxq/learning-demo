package qxq.kafka.serializer;

import org.apache.kafka.common.serialization.Serializer;
import qxq.kafka.module.User;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/5/31 11:53
 **/
public class UserSerializer implements Serializer<User> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, User data) {
        if (data == null) {
            return null;
        }
        byte[] name;
        if (data.getName() != null) {
            name = data.getName().getBytes(StandardCharsets.UTF_8);
        } else {
            name = new byte[0];
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(4+name.length+4);
        byteBuffer.putInt(name.length);
        byteBuffer.put(name);
        byteBuffer.putInt(data.getAge());
        return byteBuffer.array();
    }

    @Override
    public void close() {

    }
}
