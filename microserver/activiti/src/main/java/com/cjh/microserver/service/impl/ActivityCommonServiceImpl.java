package com.cjh.microserver.service.impl;

import com.cjh.microserver.service.api.ActivityCommonService;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cjh
 * @date 2020/3/30 22:06
 **/
@Log4j2
@Service
public class ActivityCommonServiceImpl implements ActivityCommonService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public void apply(String processDefinitionKey, Map variable) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey,"20200330666", variable);
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(task.getId(), variable);
    }

    @Override
    public List<Task> queryPendingAuditByGroup(String processDefinitionKey, String groupName) {
        // 待办
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey(processDefinitionKey)
                .taskCandidateGroup(groupName)
                .listPage(0, 10);

        log.info("有{}条待办",tasks.size());
        return tasks;
    }

    @Override
    public List<Task> queryPendingAuditByUser(String processDefinitionKey, String userId) {
        // 待办
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey(processDefinitionKey)
                .taskCandidateOrAssigned(userId)
                .listPage(0, 10);

        log.info("有{}条待办",tasks.size());
        return tasks;
    }

    @Override
    public List<HistoricTaskInstance> queryCompletedByGroup(String processDefinitionKey, String groupName) {
        // 已办
        List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery()
                .processDefinitionKey(processDefinitionKey)
                .taskCandidateGroup(groupName)
                .finished()
                .listPage(0, 10);
        log.info("有{}条已办结任务", tasks.size());
        return tasks;
    }

    @Override
    public List<HistoricTaskInstance> queryCompletedByUser(String processDefinitionKey, String userId) {
        // 已办
        List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery()
                .processDefinitionKey(processDefinitionKey)
                .taskAssignee(userId)
                .finished()
                .listPage(0, 10);
        log.info("有{}条已办结任务", tasks.size());
        return tasks;
    }

    @Override
    public void queryHistoricInstance(String businessKey) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime()
                .processInstanceBusinessKey(businessKey)
                .asc()//排序
                .list();
        if (list != null && list.size() > 0) {
            for (HistoricTaskInstance task : list) {
                log.info("流程定义ID：" + task.getProcessDefinitionId());
                log.info("流程实例ID：" + task.getId());
                log.info("任务名称：" + task.getName());
                log.info("任务处理人：" + task.getAssignee());
                log.info("任务节点ID：" + task.getTaskDefinitionKey());
                log.info("父节点ID：" + task.getParentTaskId());
                log.info("开始时间：" + task.getStartTime());
                log.info("结束时间：" + task.getEndTime());
                log.info("流程持续时间：" + task.getDurationInMillis());
                log.info("=======================================");
            }
        }
    }

    @Override
    public void claim(String processDefinitionKey, String taskId, String userId) {
        taskService.claim(taskId, userId);
        log.info("用户{},签收任务成功",userId);
    }

    @Override
    public void audit(String processDefinitionKey, String taskId, Map variable) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (StringUtils.isBlank(task.getAssignee())) {
            log.info("无未指定处理人，系统自动处理！");
            taskService.setAssignee(taskId, "system");
        }
        taskService.complete(taskId, variable);
        log.info("任务ID:" + task.getId());
        log.info("任务的办理人:" + task.getAssignee());
        log.info("任务名称:" + task.getName());
        log.info("任务的创建时间:" + task.getCreateTime());
    }
}
