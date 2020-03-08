package cn.dr.day03.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * 十一、单个流程实例的挂起与激活
 *
 */
public class SuspendProcessInstance2 {
    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到RuntimeService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //3.查询流程实例对象
        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery().processInstanceId("2501")
                .singleResult();
        //4.得到当前流程定义的实例是否都为暂停状态
        boolean suspended = processInstance.isSuspended();
        String processInstanceId = processInstance.getId();
        //5.判断
        if (suspended) {
            runtimeService.activateProcessInstanceById(processInstanceId);
            System.out.println("流程实例：" + processInstanceId + "激活");
        }else{
            runtimeService.suspendProcessInstanceById(processInstanceId);
            System.out.println("流程实例：" + processInstanceId + "挂起");
        }
    }

    /**
     * 查询当前用户(***)的任务并处理
     * 当流程实例eg:2501已经处于挂起状态，若此时要让实例继续执行，失败并抛出异常
     *  org.activiti.engine.ActivitiException: Cannot complete a suspended task
     */
    @Test
    public void completeTask(){
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();
        //3.查询当前用户的任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myholiday")
                .taskAssignee("zhangsan")
                .singleResult();
        //4.处理任务，结合当前任务列表的查询操作(任务ID为task.getId())
        taskService.complete(task.getId());
        //5.输出任务的id
        System.out.println(task.getId());
    }
}
