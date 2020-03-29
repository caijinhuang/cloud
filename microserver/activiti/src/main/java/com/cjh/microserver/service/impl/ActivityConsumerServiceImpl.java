package com.cjh.microserver.service.impl;

import com.cjh.microserver.service.api.ActivityConsumerService;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cjh
 * @date 2020/3/29 17:04
 **/
@Log4j2
@Service
public class ActivityConsumerServiceImpl implements ActivityConsumerService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public boolean startActivityDemo() {
        log.info("method startActivityDemo begin....");

        log.info("调用流程存储服务，查询部署数量："
                + repositoryService.createDeploymentQuery().count());


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("apply", "zhangsan");
        map.put("approve", "lisi");

        //流程启动
        ExecutionEntity pi1 = (ExecutionEntity) runtimeService.startProcessInstanceByKey("leave", map);

        List<Task> tq = taskService.createTaskQuery().taskAssignee("zhangsan").list();
        log.info(tq.size());
        //当前任务办理人
        String assignee = "zhangsan";
        //与任务相关的Service
        List<Task> tasks = taskService
                //创建一个任务查询对象
                .createTaskQuery()
                .taskAssignee(assignee)
                .list();
        if (tasks != null && tasks.size() > 0) {
            for (Task task : tasks) {
                log.info("任务ID:" + task.getId());
                log.info("任务的办理人:" + task.getAssignee());
                log.info("任务名称:" + task.getName());
                log.info("任务的创建时间:" + task.getCreateTime());

                log.info("流程实例ID:" + task.getProcessInstanceId());
                log.info("#####################################");
            }
        }

        log.info("method startActivityDemo end....");
        return false;
    }
}
