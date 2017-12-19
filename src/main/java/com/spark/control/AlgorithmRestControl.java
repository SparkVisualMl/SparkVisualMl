package com.spark.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spark.service.SparkJdbcForMySQLService;
import com.spark.service.WordCountServiceJava;
import com.spark.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
@RequestMapping(value = "/algorithm")
@RestController
public class AlgorithmRestControl {

    @Autowired
    private WordCountServiceJava wordCountServiceJava;
//    @Autowired
    private SparkJdbcForMySQLService sparkJdbcForMySQLService;

    private Logger logger = LoggerFactory.getLogger(AlgorithmRestControl.class);

    /**
     * WordCount
     * @param fileAddress
     * @return
     */
    @RequestMapping(value="/wordCountInFile", method = RequestMethod.GET)
    public  JSONObject getWordCountInFile(@RequestParam("fileAddress") String fileAddress) {

        String path = FileUtil.getPathFromArray(fileAddress).replace("\"","");
        System.out.println(path);
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(new HashMap().put("isExist","false")));
        if(FileUtil.checkFileIsExist(path)){
            //如果文件存在
            logger.warn("文件存在，继续执行");
            Map<String,Integer> wordCountsMap = wordCountServiceJava.run(path);
            itemJSONObj = JSONObject.parseObject(JSON.toJSONString(wordCountsMap));
        }else {
            logger.warn("文件不存在，返回警告");
            itemJSONObj=itemJSONObj;
        }
        return itemJSONObj;
    }
    @RequestMapping(value = "/sqls",method = RequestMethod.GET)
    public String sqlExecRes(@RequestParam("sqls") String sqls){
        return "sqls";
    }

    @RequestMapping(value = "/datasource/mysql",method = RequestMethod.GET)
    public String dataSourceForMysql(@RequestParam("ip") String ip,@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("port") String port,@RequestParam("dbtable") String dbtable){
        sparkJdbcForMySQLService.getMysqlDF(ip,username,password,port,dbtable);
        logger.error("ip--------------------"+ip);
        return "sqls";
    }


}
