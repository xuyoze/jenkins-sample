package com.xyz.demo.svc.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * =============================================================================
 * == @author yzoe
 * == @date 2018/3/8
 * == @desc
 * =============================================================================
 **/
@Component
public class LogTask {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(fixedDelay = 5)
    public void doLogging() {
        logger.debug("hello from demo-svc");
    }
}
