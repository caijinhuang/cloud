package com.cjh.microserver.service.impl;

import com.cjh.common.utils.MapUtil;
import com.cjh.microserver.ActivitiApplication;
import com.cjh.microserver.service.api.ActivityConsumerService;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
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
        Map map = MapUtil.builder()
                .put("apply", "小王")
                .put("approve", "老王")
                .put("groups","myGroup")
                .put("userIds","123,456")
                .build();
        activityConsumerService.apply(map);
    }

    @Test
    public void audit() {
        String singer = "456";
        List<Task> pendingTask = activityConsumerService.pendingAudit(singer);
        pendingTask.forEach(task->{
            activityConsumerService.claim(task.getId(),singer);
            activityConsumerService.audit(MapUtil.builder()
                    .put("id",task.getId())
                    .put("pass",false)
                    .build());
            log.info("任务：{},id:{},已被【{}】处理！", task.getName(), task.getId(), singer);
        });
    }

    @Test
    public void pendingAudit() {
        activityConsumerService.pendingAudit("456");
    }

    @Test
    public void go(){
        apply();
        pendingAudit();
    }
}
