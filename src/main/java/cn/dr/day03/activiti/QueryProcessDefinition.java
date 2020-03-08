package cn.dr.day03.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import java.util.List;

/**
 * 五、查询流程定义信息
 */
public class QueryProcessDefinition {
    public static void main(String[] args) {
        //1.得到ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.创建ResposityService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.得到ProcessDefinitionQuery对象(可以认为是一个查询器)
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        //4.设置条件(查询条件：流程定义的key=myholiday)，并查询出当前的所有流程定义
        List<ProcessDefinition> list = processDefinitionQuery.processDefinitionKey("myholiday")
                .orderByProcessDefinitionVersion()
                .desc().list();
        //5.输出流程定义信息
        for (ProcessDefinition processDefinition : list) {
            System.out.println("流程定义ID：" + processDefinition.getId());//myholiday:1:4
            System.out.println("流程定义名称：" + processDefinition.getName());//请假审批
            System.out.println("流程定义的key：" + processDefinition.getKey());//myholiday
            System.out.println("流程定义的版本号：" + processDefinition.getVersion());//1
            System.out.println("流程部署的ID：" + processDefinition.getDeploymentId());
        }
    }
}
