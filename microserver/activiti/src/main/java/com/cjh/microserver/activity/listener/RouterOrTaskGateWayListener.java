package com.cjh.microserver.activity.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Task;

import java.util.Map;


/**
 * 会签监听
 *
 * @author cjh
 */
@Log4j2
public class RouterOrTaskGateWayListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {
        log.info("启动监听");
        //获取流程id
        String exId = execution.getSuperExecutionId();
        //获取流程参数pass，会签人员完成自己的审批任务时会添加流程参数pass，false为拒绝，true为同意
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();
        Map variables = runtimeService.getVariables(execution.getId());
        log.info(JSON.toJSONString(variables));
        runtimeService.setVariable(execution.getId(),"userId","141304092");
    }
}