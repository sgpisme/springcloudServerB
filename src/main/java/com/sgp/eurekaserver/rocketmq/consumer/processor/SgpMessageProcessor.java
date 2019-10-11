package com.sgp.eurekaserver.rocketmq.consumer.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xtc.rocketmq.client.MQMessage;
import com.xtc.rocketmq.client.msgprocessor.MessageProcessor;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SgpMessageProcessor implements MessageProcessor {

    @Override
    public boolean handleMessage(MessageExt messageExt) {
        String body = new String(messageExt.getBody());
        System.out.println(body);
        return true;
    }

}
