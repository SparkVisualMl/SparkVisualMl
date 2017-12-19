import com.spark.utils.ConfigUtil;
import com.spark.utils.SparkUtils;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;


public class utils {

    @Test
    public void testFileUtil(){
        System.out.println("开始测试");
    }
    @Test
    public void testSparkUtilExecShell(){
        String res = SparkUtils.execSubmitShell("ls /");
        System.out.println(res+"---------");
    }
    @Test
    public void testConfigUtil() throws ConfigurationException {
        String res = ConfigUtil.getValueByKeyInConfig("spark.jar.UPLOADED_FOLDER");
        System.out.println(res);
    }
}
