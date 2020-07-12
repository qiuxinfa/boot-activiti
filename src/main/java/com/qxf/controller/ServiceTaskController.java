package com.qxf.controller;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName ServiceTaskController
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/7/9 22:52
 **/
@RestController
public class ServiceTaskController {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ManagementService managementService;

    @GetMapping("/delegate")
    public String testDelegate(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("serviceTask1");
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceEndTime().asc().list();
        for (HistoricActivityInstance instance : list){
            System.out.println("delegate已完成： "+instance.getActivityName());
        }
        return "myJavaDelegate执行完成";
    }

    @GetMapping("/behavior")
    public String testBehavior(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("serviceTask2");
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceEndTime().asc().list();
        for (HistoricActivityInstance instance : list){
            System.out.println("agenda之前，behavior已完成： "+instance.getActivityName());
        }

        Execution execution = runtimeService.createExecutionQuery().activityId("myServiceTask2").singleResult();

        managementService.executeCommand(new Command<Object>() {
            @Override
            public Object execute(CommandContext commandContext) {
                ActivitiEngineAgenda agenda = commandContext.getAgenda();
                // 驱动节点向后执行
                agenda.planTakeOutgoingSequenceFlowsOperation((ExecutionEntity)execution,false);
                return null;
            }
        });

        list = historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceEndTime().asc().list();
        for (HistoricActivityInstance instance : list){
            System.out.println("agenda之后，behavior已完成： "+instance.getActivityName());
        }

        return "myActivityBehavior执行完成";
    }
}
