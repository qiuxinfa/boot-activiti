package com.qxf.controller;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SaleLeaderController
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/7/9 20:35
 **/
@RestController
public class SaleLeaderController {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/leader")
    public String testLeader(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("reviewSaleLeader");
        Task provideNewSalesLead = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(provideNewSalesLead.getId());

        Task reviewCustomerRating = taskService.createTaskQuery().taskAssignee("qiuxinfa").singleResult();
        Task reviewMoney = taskService.createTaskQuery().taskAssignee("sam").singleResult();

        taskService.complete(reviewCustomerRating.getId());
        Map map = new HashMap(1);
        map.put("isEnoughInfo","false");
        taskService.complete(reviewMoney.getId(),map);

        // 由于 isEnoughInfo 为 false，会产生错误事件，重新执行子流程
        // 1.捕获到错误事件，提供详细材料
        Task provideDetail = taskService.createTaskQuery().singleResult();
        taskService.complete(provideDetail.getId());
        // 2.重新走子流程
        reviewCustomerRating = taskService.createTaskQuery().taskAssignee("qiuxinfa").singleResult();
        reviewMoney = taskService.createTaskQuery().taskAssignee("sam").singleResult();

        taskService.complete(reviewCustomerRating.getId());
        map.put("isEnoughInfo","true");
        taskService.complete(reviewMoney.getId(),map);

        // 完成子流程，保存信息到系统任务
        // 目前没有执行到，后续再检查一下
//        Task storeInSystem = taskService.createTaskQuery().singleResult();
//        taskService.complete(storeInSystem.getId());

        return "销售精英评审完成";
    }
}
