package top.labos.l_groupTask;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 监听器+设置组任务候选人
 */
public class GroupTaskListenerImpl implements TaskListener {
//    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("触发监听器");
        delegateTask.addCandidateUser("a");
        delegateTask.addCandidateUser("b");
        delegateTask.addCandidateUser("c");
        delegateTask.addCandidateUser("d");
        System.out.println("设置组任务候选人完成");
    }
}
