package com.algorithm.basicstatistics;import com.algorithm.interfaces.AlgorithmInterface;import org.apache.spark.sql.Dataset;import java.util.HashMap;/** * 相关性系数，包括皮尔逊相关系数和斯皮尔曼相关系数 */public class Correlation implements AlgorithmInterface {    @Override    public HashMap<String, String> getArgs() {        return null;    }    @Override    public Object getInputData() {        return null;    }    @Override    public void setInputData(Dataset<Dataset> dataset) {    }    @Override    public void setOutputData() {    }    @Override    public Dataset getOutPutData() {        return null;    }    @Override    public void compute() {    }    @Override    public boolean registerToContainer() {        return false;    }}