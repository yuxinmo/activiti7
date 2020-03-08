package top.labos.k_personTask;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * 分配个人任务方式一（直接指定办理人）
 * 分配个人任务方式二（使用流程变量）
 */
public class PersonTask02 {

    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署流程使用zip
     */
    @Test
    public void deployProcess() {
        // 得到流程部署的service
        RepositoryService repositoryService = this.processEngine.getRepositoryService();
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("diagram/personTask.zip");
        ZipInputStream zipInputStream = null;
        if (inputStream != null) {
            zipInputStream = new ZipInputStream(inputStream);
            Deployment deploy = repositoryService.createDeployment()
                    .name("请假流程")
                    .addZipInputStream(zipInputStream)
                    .deploy();
            System.out.println("部署成功:流程部署ID：" + deploy.getId());
        }else {
            System.out.println("找不到文件");
        }
    }

    /**
     * 启动并指定下一个任务的办理人【从session里面取】
     */
    @Test
    public void startProcess() {
        RuntimeService runtimeService = this.processEngine.getRuntimeService();
        String processDefinitionKey = "personTask";
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("username", "张三");
        runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        System.out.println("流程启动成功");
    }


    /**
     * 查询任务
     */
    @Test
    public void findTask() {
        TaskService taskService = this.processEngine.getTaskService();
        String assignee="李四";
        List<Task> list = taskService.createTaskQuery().taskAssignee(assignee).list();
        if(null!=list&&list.size()>0) {
            for (Task task : list) {
                System.out.println("任务ID:"+task.getId());
                System.out.println("流程实例ID:"+task.getProcessInstanceId());
                System.out.println("执行实例ID:"+task.getExecutionId());
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());
                System.out.println("任务名称:"+task.getName());
                System.out.println("任务办理人:"+task.getAssignee());
                System.out.println("################################");
            }
        }
    }

    /**
     * 办理任务并指定下一个任务的办理人【从session里面取】
     */
    @Test
    public void completeTask() {
        TaskService taskService = this.processEngine.getTaskService();
        String taskId="7502";
        Map<String,Object> variables=new HashMap<String, Object>();
//        variables.put("username", "王五");
        taskService.complete(taskId,variables);
        System.out.println("任务完成");
    }

}
