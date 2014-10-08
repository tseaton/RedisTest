import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by tylers on 10/8/14.
 */

@Configuration
public class AppConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPort(6379);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<String, Simple> redisTemplate = new RedisTemplate<String, Simple>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jacksonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisSerializer<Simple> jacksonRedisSerializer() {
        JacksonJsonRedisSerializer<Simple> jacksonJsonRedisSerializer = new JacksonJsonRedisSerializer<Simple>(Simple.class);
        return jacksonJsonRedisSerializer;
    }

    @Bean
    public RedisSerializer<String> stringRedisSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

}
