package cn.dr.day03.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;

import java.util.List;

/**
 * 八、历史数据的查看
 *
 */
public class HistoryQuery {
    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到HistoryService对象
        HistoryService historyService = processEngine.getHistoryService();
        //3.得到HistoricActivitiInstanceQuery对象
        HistoricActivityInstanceQuery historicActivityInstanceQuery =
                historyService.createHistoricActivityInstanceQuery();
        historicActivityInstanceQuery.processInstanceId("2501");//设置流程实例的id
        //4.执行查询
        List<HistoricActivityInstance> list =
                historicActivityInstanceQuery.orderByHistoricActivityInstanceStartTime().asc().list();
        //5.遍历查询结果
        for (HistoricActivityInstance instance : list) {
            System.out.println(instance.getActivityId());
            System.out.println(instance.getActivityName());
            System.out.println(instance.getProcessDefinitionId());
            System.out.println(instance.getProcessInstanceId());
            System.out.println("================================");
        }
    }

}
