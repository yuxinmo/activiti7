package cn.dr.day04.activiti;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MyTaskListener implements TaskListener  {

	public void notify(DelegateTask delegateTask) {
		System.out.println("-------MyTaskListener------------");
		delegateTask.setAssignee("zhangsan2");
	}

}
