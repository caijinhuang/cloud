package com.cjh.microserver.service.api;

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
    void pendingAudit(String groupName);

    /**
     * 审批
     * @param id 任务id
     */
    void audit(String id);
}
