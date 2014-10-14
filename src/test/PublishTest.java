import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by emilymarx on 10/13/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = MainApp.class)
@IntegrationTest
public class PublishTest {

    RedisTemplate<String, Simple> template;

    private JedisConnectionFactory getJedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName("localhost");
        factory.setPort(6379);
        factory.afterPropertiesSet();
        return factory;
    }

    private RedisTemplate<String, Simple> getRedisTemplate() {
        RedisTemplate<String, Simple> template;
       template = new RedisTemplate<String, Simple>();
        template.setConnectionFactory(getJedisConnectionFactory());
        template.afterPropertiesSet();
        return template;
    }
    @Test
    public void testPublish() {
        template = getRedisTemplate();
        template.getConnectionFactory().getConnection().publish(template.getStringSerializer().serialize("my-queue"),
            template.getStringSerializer().serialize("hello"));

        }

}
