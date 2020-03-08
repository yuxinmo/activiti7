package cn.dr.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * 四、处理当前用户的任务
 *  背后操作的表：
 *      act_hi_actinst
 *      act_hi_identitylink
 *      act_hi_taskinst
 *      act_ru_execution
 *      act_ru_identitylink
 *      act_ru_task
 *
 */
public class ActivitiCompleteTask {
    /**
     * zhangsan完成自己的任务
     * @param args
     */
    /*public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();
        //3.处理任务。结合当前用户任务列表的查询操作，任务ID：2505
        taskService.complete("2505");
    }*/

    /**
     * lisi完成自己的任务
     * @param args
     */
    /*public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();
        //3.处理任务。结合当前用户任务列表的查询操作，任务ID：2505
        taskService.complete("7502");
    }*/

    /**
     * 查询当前用户(wangwu)的任务并处理
     * @param args
     */
    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();
        //3.查询当前用户的任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myholiday")
                .taskAssignee("wangwu")
                .singleResult();//如果查询到的任务不止一个，不能使用singleResult
        //4.处理任务。结合当前用户任务列表的查询操作，任务ID：task.getId()
        taskService.complete(task.getId());
        //5.输出任务的id
        System.out.println(task.getId());
    }
}
