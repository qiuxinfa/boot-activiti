package com.qxf.config;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.io.Serializable;

/**
 * @ClassName MyJavaDelegate
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/7/9 22:39
 **/
public class MyJavaDelegate implements JavaDelegate,Serializable{
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("执行MyJavaDelegate，delegateExecution.id = "+delegateExecution.getId());
    }
}
