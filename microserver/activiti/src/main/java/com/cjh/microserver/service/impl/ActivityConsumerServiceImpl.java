package com.cjh.microserver.service.impl;

import com.cjh.common.utils.MapUtil;
import com.cjh.microserver.service.api.ActivityConsumerService;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public static final String APPLY = "apply";
    public static final String APPROVE = "approve";

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public boolean startActivity() {
        log.info("method startActivityDemo begin....");

        log.info("调用流程存储服务，查询部署数量：" + repositoryService.createDeploymentQuery().count());


        Map<String, Object> map = new HashMap<String, Object>();
        map.put(APPLY, "张三");
        map.put(APPROVE, "李四");

        //流程启动
        ExecutionEntity pi1 = (ExecutionEntity) runtimeService.startProcessInstanceByKey("leave", map);

        //当前任务办理人
        String assignee = "张三";
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

    @Override
    public void apply(Map map) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave", map);
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(task.getId(), map);
    }

    @Override
    public List<Task> pendingAudit(String key) {
        // 待办
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("leave")
//                .taskCandidateUser("123")
//                .taskCandidateGroup(key)
                .taskCandidateOrAssigned(key)
                .listPage(0, 10);

        log.info("有{}条待办",tasks.size());
        tasks.forEach(task->{
            show(task);
        });
        log.info("展示结束");
        return tasks;
    }

    @Override
    public List<HistoricTaskInstance> queryCompleted(String groupName){
        // 已办
        List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery()
                .processDefinitionKey("leave")
                .taskAssignee(groupName)
                .finished()
                .listPage(0,10);
        log.info("有{}条已办结任务",tasks.size());
        tasks.forEach(task->{
           show(task);
        });
        log.info("展示结束");
        return tasks;
    }

    private void show(TaskInfo task){
        log.info("任务ID:" + task.getId());
        log.info("任务的办理人:" + task.getAssignee());
        log.info("任务名称:" + task.getName());
        log.info("任务的创建时间:" + task.getCreateTime());
        log.info("#####################################");
    }

    @Override
    public void claim(String taskId, String userId) {
        taskService.claim(taskId, userId);
        log.info("用户{},签收任务成功",userId);
    }

    @Override
    public void audit(Map map) {
        String id = MapUtils.getString(map, "id");
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        log.info("任务ID:" + task.getId());
        log.info("任务的办理人:" + task.getAssignee());
        log.info("任务名称:" + task.getName());
        log.info("任务的创建时间:" + task.getCreateTime());
        taskService.complete(id, map);
    }
}
