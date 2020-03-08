package cn.dr.day03.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

/**
 * 六、删除已经部署的流程定义
 *  背后影响的表(同流程定义部署影响到的表)：
 *      act_re_deployment   部署信息
 *      act_re_procdef      流程定义的信息
 *      act_ge_bytearray    流程定义的bpmn文件和png文件
 *  注意事项：
 *      1.当正在执行的一套流程没有完全审批结束的时候，此时若删除该流程定义信息就会失败(默认值false)
 *      2.如果需要强制删除，可以使用repositoryService.deleteDeployment("1", true),
 *          参数true代表级联删除(先删除没有完成的流程结点，最后删除流程定义信息)
 *          参数false(默认值)代表不级联删除
 */
public class DeleteProcessDefinition {
    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.创建RespositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.执行删除流程定义(参数代表流程部署的id)
        repositoryService.deleteDeployment("1");
//      <==> repositoryService.deleteDeployment("1", false);
//        repositoryService.deleteDeployment("1", true);
    }
}
