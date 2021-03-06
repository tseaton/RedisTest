import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Configuration
public class RedisFun {

    @Autowired
    private RedisTemplate<String, Simple> redisTemplate;


    @RequestMapping("/test/")
    @ResponseBody
    public Simple demonstrateRedis() {

        Simple simple = new Simple();
        simple.setTest("bar");
        simple.setTest2("baz");

        redisTemplate.opsForValue().set(simple.getTest(), simple);

        return redisTemplate.boundValueOps(simple.getTest()).get();
    }

    @ServiceActivator(inputChannel = "redisChannel")
    public void testMethod(Message<String> message) {
        System.out.println(message.getPayload());
    }

}
