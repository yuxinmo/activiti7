package cn.dr.day03.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;

/**
 * 七、需求：
 * 1.从Activiti的act_ge_bytearray表中读取两个资源文件
 * 2.将两个资源文件保存到指定路径
 * <p>
 * 技术方案:
 * 1.第一种方式：使用activiti的api来实现
 * 2.第二种方式(原理层面)：使用jdbc对blob类型、clob类型数据的读取并保存
 * IO流转换采用commons-io.jar包解决IO操作
 * <p>
 * 真是应用场景：用户查看该请假流程具体由哪些步骤要走
 */
public class QueryBpmnFile {
    public static void main(String[] args) throws IOException {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.得到查询器：ProcessDefinitionQuery对象
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        //4.设置查询条件
        processDefinitionQuery.processDefinitionKey("myholiday");//参数是流程定义的key
        //5.执行查询操作，查询出指定的流程定义
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
        //6.通过流程定义信息，得到部署的ID
        String deploymentId = processDefinition.getDeploymentId();
        //7.通过repositoryService的方法，实现读取图片信息及bpmn文件信息(输入流)
        /*
        repositoryService.getResourceAsStream(s1, s2) 第一个参数:流程定义部署的ID，第二个:资源名称
        processDefinition.getDiagramResourceName() 获取png图片资源的名称
        processDefinition.getResourceName()        获取bpmn文件的名称
         */
        InputStream pngIs = repositoryService
                .getResourceAsStream(deploymentId, processDefinition.getDiagramResourceName());
        InputStream bpmnIs = repositoryService
                .getResourceAsStream(deploymentId, processDefinition.getResourceName());
        //8.构建出OutputStream流
        FileOutputStream pngOs =
                new FileOutputStream("D:\\other\\" + processDefinition.getDiagramResourceName());
        FileOutputStream bpmnOs =
                new FileOutputStream("D:\\other\\" + processDefinition.getResourceName());
        //9.输入/输出流转换 commons-io.jar中的方法
        IOUtils.copy(pngIs, pngOs);
        IOUtils.copy(bpmnIs, bpmnOs);
        //10.关闭流
        pngOs.close();
        bpmnOs.close();
        pngIs.close();
        bpmnIs.close();
    }

    /**
     * 通过流程定义对象获取流程定义资源，获取 bpmn 和 png
     * @throws IOException
     */
    public void getProcessResources1() throws IOException {
        // 流程定义id
        String processDefinitionId = "";
        // 获取repositoryService
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine
                .getRepositoryService();

        // 流程定义对象
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        //获取bpmn
        String resource_bpmn = processDefinition.getResourceName();
        //获取png
        String resource_png =
                processDefinition.getDiagramResourceName();
        // 资源信息
        System.out.println("bpmn： " + resource_bpmn);
        System.out.println("png： " + resource_png);
        File file_png = new File("d:/purchasingflow01.png");
        File file_bpmn = new File("d:/purchasingflow01.bpmn");
        // 输出bpmn
        InputStream resourceAsStream = null;
        resourceAsStream = repositoryService.getResourceAsStream(
                processDefinition.getDeploymentId(), resource_bpmn);
        FileOutputStream fileOutputStream = new
                FileOutputStream(file_bpmn);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            fileOutputStream.write(b, 0, len);
        }
        // 输出图片
        resourceAsStream = repositoryService.getResourceAsStream(
                processDefinition.getDeploymentId(), resource_png);
        fileOutputStream = new FileOutputStream(file_png);
        // byte[] b = new byte[1024];
        // int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            fileOutputStream.write(b, 0, len);
        }
    }


    /**
     * 通过查询流程部署信息获取流程定义资源, 获取流程定义图片资源
     *  1.使用 repositoryService 的 getDeploymentResourceNames方法可以获取指定部署下得所有文件的名称
     *  2.使用 repositoryService 的 getResourceAsStream 方法传入部署 ID和资源图片名称可以获取部署下
     *  指定名称文件的输入流
     * @throws IOException
     */
    public void getProcessResources2() throws IOException {
        //流程部署id
        String deploymentId = "9001";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 通过流程引擎获取repositoryService
        RepositoryService repositoryService = processEngine
                .getRepositoryService();
        //读取资源名称
        List<String> resources =
                repositoryService.getDeploymentResourceNames(deploymentId);
        String resource_image = null;
        //获取图片
        for (String resource_name : resources) {
            if (resource_name.indexOf(".png") >= 0) {
                resource_image = resource_name;
            }
        }
        //图片输入流
        InputStream inputStream =
                repositoryService.getResourceAsStream(deploymentId, resource_image);
        File exportFile = new File("d:/holiday.png");
        FileOutputStream fileOutputStream = new
                FileOutputStream(exportFile);
        byte[] buffer = new byte[1024];
        int len = -1;
        //输出图片
        while ((len = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, len);
        }
        inputStream.close();
        fileOutputStream.close();
    }
}
