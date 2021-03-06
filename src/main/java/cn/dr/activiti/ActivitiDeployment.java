package cn.dr.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * 一、流程定义的部署
 *  此操作影响的activiti表有：
 *      act_re_deployment   部署信息
 *      act_re_procdef      流程定义的一些信息
 *      act_ge_bytearray    流程定义的bpmn文件及png文件
 *
 */
public class ActivitiDeployment {
    /**
     * 流程定义部署
     */
    public static void main(String[] args) {
        //1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday.bpmn")
                .addClasspathResource("diagram/holiday.png")
                .name("请假申请单流程")
                .deploy();
        //4.输出部署的一些信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }

    /**
     * 流程定义部署(zip文件部署方式)
     *      流程制作出来后要上传服务器，zip文件更便于上传
     * @param args
     */
    /*public static void main(String[] args) {
        //1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.转换出ZipInputStream流对象
        InputStream inputStream =
                ActivitiDeployment.class.getClassLoader().getResourceAsStream("diagram/holidayBPMN.zip");
        //4.将InputStream流转换成ZipInputStream流
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        //5.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .name("请假申请单流程")
                .deploy();
        //6.输出部署的一些信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }*/
}
