package com.cjh.microserver.service.api;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * @author cjh
 * @date 2020/3/29 17:03
 **/
public interface ActivityConsumerService {

    /**
     * 流程开始
     * @return
     */
    boolean startActivity();

    /**
     * 提交申请
     * @param apply 申请人
     * @param approve 审批人
     */
    void apply(Map map);

    /**
     * 待审批
     * @param groupName
     */
    List<Task> pendingAudit(String key);

    /**
     * 已办结
     * @param groupName
     * @return
     */
    List<HistoricTaskInstance> queryCompleted(String groupName);

    /**
     *
     * @param taskId
     * @param userId
     */
    void claim(String taskId, String userId);

    /**
     * 审批
     * @param map map
     */
    void audit(Map map);
}
