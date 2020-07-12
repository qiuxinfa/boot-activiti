package com.qxf.config;


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.delegate.ActivityBehavior;

/**
 * @ClassName MyActivitiBehavior
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/7/9 22:41
 **/
public class MyActivityBehavior implements ActivityBehavior {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("执行MyActivityBehavior，其delegateExecution.id = "+delegateExecution.getId());
    }
}
