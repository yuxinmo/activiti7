package cn.dr.day04.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * 十二、启动流程实例，动态设置assignee
 *
 */
public class AssigneeUEL {
    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到runtimeService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //3.设置assignee的取值，用户可以在界面上设置流程的执行人
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("assignee0", "zhangsan1");//${assignee0}
        map.put("assignee1", "lisi1");//${assignee1}
        map.put("assignee2", "wangwu1");//${assignee2}
        //4.启动流程实例，同时设置流程定义的assignee的值
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myholiday1", map);
        //5.输出
        System.out.println(processInstance.getName());
    }

    @Test
    public void deployActiviti(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday2.bpmn")
                .addClasspathResource("diagram/holiday2.png")
                .name("请假申请单流程2")
                .deploy();
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }

    @Test
    public void startActivitiInstance(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myholiday2");
        System.out.println("流程部署的ID：" + processInstance.getDeploymentId());//null
        System.out.println("流程定义的ID：" + processInstance.getProcessDefinitionId());//myholiday:1:4
        System.out.println("流程实例的ID：" + processInstance.getId());//2501
        System.out.println("活动的ID：" + processInstance.getActivityId());//null
    }

    @Test
    public void completeTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myholiday2")
                .taskAssignee("zhangsan2")
                .singleResult();
        taskService.complete(task.getId());
        System.out.println(task.getId());
    }
}
