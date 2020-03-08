package top.labos.k_personTask;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 监听器
 */
public class TaskListenerImpl implements TaskListener {
//    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("出发监听器。。。。。。。。。。。。");
        String assignee = "李四";
        /*
        从session中得到上级用户，
        指定办理人
         */
        delegateTask.setAssignee(assignee);
    }
}
