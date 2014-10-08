import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
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

        //redisTemplate.boundValueOps(simple.getTest()).set(simple);
        redisTemplate.opsForValue().set(simple.getTest(), simple);

        Simple foo = redisTemplate.boundValueOps(simple.getTest()).get();
        return foo;
    }
}
