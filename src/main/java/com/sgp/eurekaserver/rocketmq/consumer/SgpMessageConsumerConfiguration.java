package com.sgp.eurekaserver.rocketmq.consumer;

import com.sgp.eurekaserver.rocketmq.consumer.processor.SgpMessageProcessor;
import com.xtc.rocketmq.client.PushConsumer;
import com.xtc.rocketmq.client.listener.DefaultMessageListenerConCurrently;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SgpMessageConsumerConfiguration {

    @Value("${rocketmq.consumer.sgpMessageConsumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.sgpMessageConsumer.instanceName}")
    private String instanceName;
    @Value("${rocketmq.consumer.sgpMessageConsumer.groupName}")
    private String groupName;
    @Value("${rocketmq.consumer.sgpMessageConsumer.topic}")
    private String topic;
    @Value("${rocketmq.consumer.sgpMessageConsumer.tags}")
    private String tags;
    @Value("${rocketmq.consumer.sgpMessageConsumer.consumeThreadMin}")
    private Integer consumeThreadMin;
    @Value("${rocketmq.consumer.sgpMessageConsumer.consumeThreadMax}")
    private Integer consumeThreadMax;
    @Value("${rocketmq.consumer.sgpMessageConsumer.consumeTimeout}")
    private Long consumeTimeout;

    @Autowired
    private SgpMessageProcessor messageProcessor;

    @Bean(name = "sgpMessageConsumer")
    public PushConsumer getPushconsumer() throws Exception {
        DefaultMessageListenerConCurrently messageListener = new DefaultMessageListenerConCurrently();
        messageListener.setMessageProcessor(messageProcessor);
        PushConsumer pushConsumer = new PushConsumer(namesrvAddr, groupName, ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET, // 首次从尾部开始，后续接着上次位置开始
                topic, tags, messageListener);
        if (StringUtils.isNotEmpty(instanceName)) {
            pushConsumer.setInstancename(instanceName);
        }
        if (consumeThreadMin > 0) {
            pushConsumer.setConsumeThreadMin(consumeThreadMin);
        }
        if (consumeThreadMax > 0) {
            pushConsumer.setConsumeThreadMax(consumeThreadMax);
        }
        if (consumeTimeout > 0) {
            pushConsumer.setConsumeTimeout(consumeTimeout);
        }
        pushConsumer.start();
        return pushConsumer;
    }

}
