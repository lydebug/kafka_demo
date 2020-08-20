package fudan.leon.kafka_demo.controller;

import fudan.leon.kafka_demo.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liyang27
 * @Date: 2020/7/6 17:36
 * @Description:
 */
@RestController
public class SendMessageController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("send/{message}")
    public void send(@PathVariable String message) {
        ListenableFuture<SendResult<String, Message>> future = this.kafkaTemplate.send("test", new Message("hello ", message));
        future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.info("message:{} send fail,reason:{}", message, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Message> stringStringSendResult) {
                logger.info("send message: {} successfully,offset=[{}]", message, stringStringSendResult.getRecordMetadata().offset());
            }
        });
    }
}
