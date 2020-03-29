package com.cjh.microserver.service.impl;

import com.cjh.microserver.ActivitiApplication;
import com.cjh.microserver.service.api.ActivityConsumerService;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author cjh
 * @date 2020/3/29 17:28
 **/
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ActivitiApplication.class)
public class ActivityConsumerServiceImplTest {

    @Autowired
    ActivityConsumerService activityConsumerService;

    @Test
    public void startActivityDemo() {
        activityConsumerService.startActivityDemo();
    }
}
