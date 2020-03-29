package com.cjh.microserver.service.impl;

import com.cjh.common.utils.MapUtil;
import com.cjh.microserver.ActivitiApplication;
import com.cjh.microserver.service.api.ActivityConsumerService;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * @author cjh
 * @date 2020/3/29 17:28
 **/
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ActivitiApplication.class)
public class ActivityConsumerServiceImplTest {

    @Autowired
    ActivityConsumerService activityConsumerService;

    @Test
    public void startActivityDemo() {
        activityConsumerService.startActivity();
    }


    @Test
    public void apply() {
        Map map = MapUtil.builder().put("apply", "小菜提交申请1").put("approve", "小叶审批1").put("groups","myGroup666").build();
        activityConsumerService.apply(map);
    }

    @Test
    public void audit() {
        activityConsumerService.audit("37503");
    }

    @Test
    public void pendingAudit() {
        activityConsumerService.pendingAudit("小叶审批1");
    }

    @Test
    public void go(){
        apply();
        pendingAudit();
    }
}
