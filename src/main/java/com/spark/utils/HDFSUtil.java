package com.spark.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


public class HDFSUtil {
    final private static Logger logger = LoggerFactory.getLogger(HDFSUtil.class);


    /**
     * 保存下载的jar包，并且将保存jar包的目录添加到环境变量里面
     * @param multipartFileList file content
     * @return uploadPath
     */
    public static String saveUploadedFilesToHadoop(List<MultipartFile> multipartFileList){
        String uploadPath="";
        for(MultipartFile file:multipartFileList){
            if (file.isEmpty()) {
                continue; //下一部分
            }
            try{
                byte[] bytes = file.getBytes();
                String uploadHDFSDir = ConfigUtil.getValueByKeyInConfig("spark.jar.UPLOADED_HDFS_FOLDER");
                File fileMake = new File("/"+uploadHDFSDir+"/"+file.getOriginalFilename());
                logger.info(fileMake.toString());
                if(!fileMake.exists()){
                    uploadPath = fileMake.toString();
                    writeFile(uploadPath,bytes);
                }else {
                    logger.warn("文件已经存在");
                }

            }catch (IOException e){
                logger.error(e.getMessage());
            }catch (ConfigurationException e1){
                logger.error(e1.getMessage());

            }
        }
        return uploadPath;

    }

    /**
     * 上传文件到HDFS  创建目录
     * @param dirPath
     * @return
     */
    public static boolean createDir(String dirPath){
        boolean isSuccess = true;

        Configuration conf = new Configuration();
        try {
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path(dirPath);
            fs.create(path);
            fs.close();
        }catch (IOException e){
            isSuccess=false;
            logger.error(e.getMessage());
        }
        return isSuccess;
    }

    /**
     * 上传文件到HDFS
     * 删除目录
     * @param dirPath
     * @param isRecursive
     * @return
     */
    public static boolean deleteDir(String dirPath,boolean isRecursive){

        boolean isSuccess = true;

        Configuration conf = new Configuration();
        try {
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path(dirPath);
            fs.delete(path,isRecursive);
            fs.close();
        }catch (IOException e){
            isSuccess=false;
            logger.error(e.getMessage());
        }
        return isSuccess;
    }

    /**
     * 写文件
     * @param filePath
     * @param bytes
     * @return
     */
    public static boolean writeFile(String filePath,byte [] bytes){
        boolean isSuccess = true;

        Configuration conf = new Configuration();
        try {
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path(filePath);
            FSDataOutputStream out = fs.create(path);
            out.write(bytes);
            fs.close();
        }catch (IOException e){
            isSuccess=false;
            logger.error(e.getMessage());
        }
        return isSuccess;
    }

    /**
     * 读文件
     * @param filePath
     * @return
     */
    public static String readFile(String filePath){
        StringBuffer sb = new StringBuffer();
        try {
            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path(filePath);

            if(fs.exists(path)){
                FSDataInputStream is = fs.open(path);
                FileStatus status = fs.getFileStatus(path);
                byte[] buffer = new byte[Integer.parseInt(String.valueOf(status.getLen()))];
                is.readFully(0, buffer);
                is.close();
                fs.close();
                sb.append(buffer.toString());
            }
        }catch (IOException e){
            logger.error(e.getMessage());
            return e.getMessage();
        }

        return sb.toString();
    }

    /**
     * 上传本地文件到HDFS
     * @param srcFile
     * @param distFile
     * @return
     */
    public static boolean uploadLocalFileToHdfs(String srcFile,String distFile){
        boolean isSuccess = true;
        try {
            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(conf);
            Path src = new Path(srcFile);
            Path dst = new Path(distFile);
            fs.copyFromLocalFile(src, dst);
            fs.close();
        }catch (IOException e){
            isSuccess = false;
            logger.error(e.getMessage());
        }
        return isSuccess;
    }

    /**
     * 删除文件
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath){
        boolean isSuccess = true;
        try {
            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path(filePath);
            fs.delete(path);
            fs.close();
        }catch (IOException e){
            isSuccess = false;
            logger.error(e.getMessage());
        }
        return isSuccess;
    }

    /**
     * 获取给定目录下的所有子目录以及子文件
     * @param path
     * @return List<String>
     * @throws IOException
     */

    public static List<String> getFile(Path path){

        List<String> pathNames = new ArrayList<String>();

        try {
            Configuration conf = new Configuration();

            FileSystem fs = FileSystem.get(conf);
            FileStatus[] fileStatus = fs.listStatus(path);
            for(int i=0;i<fileStatus.length;i++){
                if(fileStatus[i].isDirectory()){
                    Path p = new Path(fileStatus[i].getPath().toString());
                    getFile(p);
                }else{
                    pathNames.add(fileStatus[i].getPath().toString());
                }
            }
        }catch (IOException e){
            logger.error(e.getMessage());
        }

        return pathNames;
    }

    /**
     * 查找某个文件在HDFS集群的位置
     * @Title:
     * @Description:
     * @param
     * @return Map<String,String> 文件在HDFS集群的位置
     * @throws
     */
    public static Map<String,String> getFileClusterInfo(String filePath){
        Configuration conf = new Configuration();
        Map<String,String> maps = new HashMap<String,String>();
        try {
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path(filePath);

            FileStatus status = fs.getFileStatus(path);
            BlockLocation[] locations = fs.getFileBlockLocations(status, 0, status.getLen());

            int length = locations.length;
            for(int i=0;i<length;i++){
                String[] hosts = locations[i].getHosts();
                logger.info("block_" + i + "_location:" + hosts[i]);
                maps.put("block_" + i + "_location:",hosts[i]);
            }
        }catch (IOException e){
            logger.error(e.getMessage());
        }

        return maps;
    }

    /**
     * HDFS集群上所有节点名称信息
     * @return HDFS集群上所有节点名称信息 List<String> nodeNames
     * @throws IOException IOException
     */
    public static List<String> getHDFSNode(){
        List<String> nodeNames = new ArrayList<String>();
        Configuration conf = new Configuration();
        try {
            FileSystem fs = FileSystem.get(conf);
            DistributedFileSystem dfs = (DistributedFileSystem)fs;
            DatanodeInfo[] dataNodeStats = dfs.getDataNodeStats();

            for(int i=0;i<dataNodeStats.length;i++){
                logger.info("DataNode_" + i + "_Node:" + dataNodeStats[i].getHostName());
                nodeNames.add("DataNode_" + i + "_Node:" + dataNodeStats[i].getHostName());
            }
        }catch (IOException e){
            logger.error(e.getMessage());

        }
        return nodeNames;

    }
}
