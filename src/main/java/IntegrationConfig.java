import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.redis.inbound.RedisInboundChannelAdapter;
import org.springframework.integration.support.converter.SimpleMessageConverter;
import org.springframework.messaging.MessageChannel;


@Configuration
public class IntegrationConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public MessageChannel redisChannel() {
        DirectChannel directChannel = new DirectChannel();
        directChannel.setComponentName("redisChannel");
        return directChannel;
    }

    @Bean
    public SimpleMessageConverter simpleMessageConverter() {
        return new SimpleMessageConverter();
    }

    @Bean
    public RedisInboundChannelAdapter redisInboundChannelAdapter() {
        RedisInboundChannelAdapter redisInboundChannelAdapter = new RedisInboundChannelAdapter(redisConnectionFactory);
        redisInboundChannelAdapter.setTopics("test");
        redisInboundChannelAdapter.setMessageConverter(simpleMessageConverter());
        redisInboundChannelAdapter.setOutputChannel(redisChannel());
        return redisInboundChannelAdapter;
    }

}
