package meng.redis.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    @Nullable
    @Override
    public byte[] serialize(@Nullable T t) throws SerializationException {
        if (t == null) {
            return null;
        }
        try {
            //todo balabala
        } catch (Exception ex) {
            throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
        }

        return null;
    }

    @Nullable
    @Override
    public T deserialize(@Nullable byte[] bytes) throws SerializationException {
        return null;
    }
}
