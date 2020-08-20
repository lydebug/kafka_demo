package fudan.leon.kafka_demo.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Author: liyang27
 * @Date: 2020/7/7 09:14
 * @Description:
 */
@Component
public class KafkaMessageListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
    @KafkaListener(topics = "test",groupId = "test-consumer")
    public void listen(String message){
        logger.info("接收消息：{}",message);
    }
    */
    /*
    @KafkaListener(topics = "test",groupId = "test-consumer")
    public void listen(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition){
        logger.info("接收消息：{},partition:{}",message,partition);
    }
    */

    @KafkaListener(topics = "test", groupId = "test-consumer")
    public void listen(Message message) {
        logger.info("接收消息：{}", message);
    }
}
