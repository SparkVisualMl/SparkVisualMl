import com.spark.utils.HDFSUtil;

import org.apache.hadoop.fs.Path;
import org.junit.Test;
import java.io.IOException;
public class HDFSUtilsTest {
    @Test
    public void testListFiles() throws IOException{
        Path path = new Path("/user");
        System.out.println("---------------------------");
        System.out.println(HDFSUtil.getFile(path));
    }
    @Test
    public void testListFiles1() throws IOException{
        System.out.println("---------------------------");
        System.out.println(HDFSUtil.uploadLocalFileToHdfs("/usr/local/bigdata/hadoop-2.7.3/etc/hadoop/yarn-site.xml","/tian_chi_data/yarn-site2.xml"));
    }

    @Test
    public void testListFiles2() throws IOException{
        System.out.println("---------------------------");
        System.out.println(HDFSUtil.writeFile("/tian_chi_data/yarn-site3.xml","test".getBytes()));
    }
}
