package com.sgp.eurekaserver.rocketmq.configuration;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    public MyPropertyPlaceholderConfigurer(){

        this.setIgnoreResourceNotFound(true);
        final List<Resource> resourceLst = new ArrayList<Resource>();
        resourceLst.add(new ClassPathResource("rocketmq/rocketmq.properties"));
        this.setLocations(resourceLst.toArray(new Resource[]{}));
    }

}
