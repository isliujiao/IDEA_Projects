import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * @author liujiao
 * @date 2023/6/26 21:15
 */
public class ActivitiTest {
    /**
     * 使用activiti提供的默认方式创建sql表
     */
    @Test
    public void testCreateDbTable() {
        //使用classpath下的activiti.cfg.xml中的配置创建processEngine
        //getDefaultProcessEngine默认从吗resources读取activiti.cfg.xml文件
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }
}
