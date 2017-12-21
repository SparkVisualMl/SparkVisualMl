import com.spark.utils.ConfigUtil;
import com.spark.utils.SparkUtils;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;

import java.io.IOException;


public class utils {

    @Test
    public void testFileUtil(){
        System.out.println("开始测试");
    }


    @Test
    public void testSparkUtilExecShell(){
        String res = SparkUtils.execSubmitShell("ls /usr/local/bigdata");
        System.out.printf("---------");
        System.out.println(res+"---------");
    }

    @Test
    public void testSparkUtilExecShellCmds() throws IOException{
        System.out.printf("---------");
        String res = SparkUtils.execSubmitShellArray("ls /usr/local/bigdata");
        System.out.println(res+"---------");
    }
    @Test
    public void testConfigUtil() throws ConfigurationException {
        String res = ConfigUtil.getValueByKeyInConfig("spark.jar.UPLOADED_FOLDER");
        System.out.println(res);
    }
    @Test
    public void testThreadExec(){

        String res = SparkUtils.execThreadCmds("sh /usr/local/bigdata/spark-2.2.0-bin-hadoop2.7/sparkPiShell");
        System.out.printf("res--------------"+res);
    }
}
