package com.cjh.microserver.service.impl;

import com.cjh.common.utils.MapUtil;
import com.cjh.microserver.ActivitiApplication;
import com.cjh.microserver.service.api.ActivityCommonService;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cjh
 * @date 2020/3/31 21:47
 **/
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ActivitiApplication.class)
public class ActivityRouterTest {

    @Autowired
    ActivityCommonService activityCommonService;

    private static final String PROCESS_DEFINITION_KEY = "policyAudit";

    @Test
    public void apply() {
        List<String> signList = new ArrayList<>();
        signList.add("14130409a");
        signList.add("14130409b");
        signList.add("14130409c");
        Map map = MapUtil.builder()
                .put("opt", 1)
                .put("router", 1)
                .put("userList", signList)
                .build();
        activityCommonService.apply(PROCESS_DEFINITION_KEY, map);
    }

    @Test
    public void queryPendingAuditByGroup() {
    }

    @Test
    public void queryPendingAuditByUser() {
    }

    @Test
    public void queryCompletedByGroup() {
    }

    @Test
    public void queryCompletedByUser() {
    }

    @Test
    public void queryHistoricInstance() {
    }

    @Test
    public void getProcessDefinition() {
    }

    @Test
    public void claim() {
    }

    @Test
    public void audit() {
        List<Task> tasks = activityCommonService.queryPendingAuditByUser(PROCESS_DEFINITION_KEY,"141304092");
        for (Task task : tasks) {
            show(task);
            List<String> signList = new ArrayList<>();
            signList.add("14130409666");
//            signList.add("14130409667");
//            signList.add("14130409668");
            activityCommonService.audit(PROCESS_DEFINITION_KEY,task.getId(),MapUtil.builder()
//                    .put("auditOpt",2)
//                    .put("opt", 2)
//                    .put("router", 2)
                    .put("userList", signList)
                    .build());
        }
    }

    private void show(TaskInfo task){
        log.info("任务ID:" + task.getId());
        log.info("任务的办理人:" + task.getAssignee());
        log.info("任务名称:" + task.getName());
        log.info("任务的创建时间:" + task.getCreateTime());
        log.info("任务定义Key:" + task.getTaskDefinitionKey());
        log.info("#####################################");
    }

}
