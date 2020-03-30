package com.cjh.microserver.service.api;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * 通用流程实例
 * @author cjh
 * @date 2020/3/29 17:03
 **/
public interface ActivityCommonService {


    /**
     * 提交申请
     * @param processDefinitionKey 流程定义的Key
     * @param variable 变量
     */
    void apply(String processDefinitionKey,Map variable);

    /**
     * 查询待办任务-用户组
     * @param processDefinitionKey 流程定义key
     * @param groupName 分组名
     * @return
     */
    List<Task> queryPendingAuditByGroup(String processDefinitionKey, String groupName);

    /**
     * 查询-用户
     * @param processDefinitionKey 流程定义key
     * @param userId userId
     * @return
     */
    List<Task> queryPendingAuditByUser(String processDefinitionKey, String userId);

    /**
     * 查询已办结任务 - 用户组
     * @param processDefinitionKey 流程定义key
     * @param groupName 分组名
     * @return
     */
    List<HistoricTaskInstance> queryCompletedByGroup(String processDefinitionKey, String groupName);

    /**
     * 查询已办结任务 - 用户
     * @param processDefinitionKey 流程定义key
     * @param userId userId
     * @return
     */
    List<HistoricTaskInstance> queryCompletedByUser(String processDefinitionKey, String userId);

    /**
     * 通过流程实例查询历史实例
     * @param businessKey 业务id
     */
    void queryHistoricInstance(String businessKey);

    /**
     * 任务签收
     * @param processDefinitionKey 流程定义key
     * @param taskId 任务id
     * @param userId userId
     */
    void claim(String processDefinitionKey, String taskId, String userId);

    /**
     * 任务审批
     * @param processDefinitionKey 流程定义key
     * @param taskId 任务id
     * @param variable 审批参数
     */
    void audit(String processDefinitionKey,String taskId, Map variable);
}
