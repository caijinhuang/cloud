package com.cjh.microserver.service.impl;

import com.cjh.common.utils.MapUtil;
import com.cjh.microserver.ActivitiApplication;
import com.cjh.microserver.service.api.ActivityCommonService;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjh
 * @date 2020/3/30 22:14
 **/
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ActivitiApplication.class)
public class ActivityCommonServiceImplTest {

    @Autowired
    ActivityCommonService activityCommonService;

    private static final String PROCESS_DEFINITION_KEY = "applyHoliday";


    @Test
    public void apply() {
        // 添加会签
        List<String> signList = new ArrayList<>();
        signList.add("141304092");
        signList.add("141304093");
        signList.add("141304094");
        activityCommonService.apply(PROCESS_DEFINITION_KEY, MapUtil.builder()
                .put("userId", "141304091")
                .put("signList", signList)
                .build());
    }

    @Test
    public void queryPendingAuditByGroup() {
    }

    @Test
    public void queryPendingAuditByUser() {
        List<Task> tasks = activityCommonService.queryPendingAuditByUser(PROCESS_DEFINITION_KEY,"141304093");
        for (Task task : tasks) {
            show(task);
            List<String> signList = new ArrayList<>();
//            signList.add("141304092");
            signList.add("141304093");
//            signList.add("141304094");
            activityCommonService.audit(PROCESS_DEFINITION_KEY,task.getId(),MapUtil.builder()
                    .put("pass",true)
//                    .put("signList", signList)
                    .build());
        }
    }

    @Test
    public void queryCompletedByGroup() {
    }

    @Test
    public void queryCompletedByUser() {
        List<HistoricTaskInstance> hisTask = activityCommonService.queryCompletedByUser(PROCESS_DEFINITION_KEY,"141304093");
        for (HistoricTaskInstance task : hisTask) {
            log.info("##################################\n");
            show(task);
        }
    }



    @Test
    public void claim() {
        activityCommonService.claim(PROCESS_DEFINITION_KEY, "55007", "system");
    }

    @Test
    public void audit() {

    }

    private void show(TaskInfo task){
        log.info("任务ID:" + task.getId());
        log.info("任务的办理人:" + task.getAssignee());
        log.info("任务名称:" + task.getName());
        log.info("任务的创建时间:" + task.getCreateTime());
        log.info("#####################################");
    }

    @Test
    public void queryHistoricInstance() {
        activityCommonService.queryHistoricInstance("20200330666");
    }
}
