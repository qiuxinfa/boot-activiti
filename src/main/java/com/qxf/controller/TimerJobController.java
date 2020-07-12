package com.qxf.controller;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName TimerJobController
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/7/8 23:12
 **/
@RestController
public class TimerJobController {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/job")
    public String testTimerJob() throws InterruptedException {
        // 启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("timeJobProcess");
        // 查询当前任务
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        for (Task task : taskList){
            System.out.println("before  任务名称： "+task.getName());
        }
        // 触发定时任务边界事件
        Thread.sleep(1000*15);
        // 查询当前任务
        taskList = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        for (Task task : taskList){
            System.out.println("after  任务名称： "+task.getName());
        }

        return "执行完成";
    }
}
