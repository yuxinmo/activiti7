package cn.dr.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * 三、查询当前用户的任务列表
 */
public class ActivitiTaskQuery {
    /**
     * zhangsan完成自己任务列表的查询
     * @param args
     */
    /*public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();
        //3.根据流程定义的key和负责人assignee来实现当前用户的任务列表查询
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("myholiday")
                .taskAssignee("zhangsan")
                .list();
        //4.任务列表的展示
        for (Task task : taskList) {
            System.out.println("流程实例ID：" + task.getProcessInstanceId());
            System.out.println("任务ID：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
            System.out.println("===============================");
        }
    }*/

    /**
     * lisi完成自己任务列表的查询
     *
     * @param args
     */
    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();
        //3.根据流程定义的key和负责人assignee来实现当前用户的任务列表查询
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myholiday")
                .taskAssignee("lisi")
                .singleResult();
        //4.任务列表的展示
        System.out.println("流程实例ID：" + task.getProcessInstanceId());//2501
        System.out.println("任务ID：" + task.getId());//7502
        System.out.println("任务负责人：" + task.getAssignee());//lisi
        System.out.println("任务名称：" + task.getName());//部门经理审批
        System.out.println("===============================");
    }
}
