package com.algorithm.basicstatistics;import com.algorithm.Algorithm;import org.apache.spark.sql.Dataset;import org.apache.spark.sql.Row;import java.util.HashMap;/** * 相关性系数，包括皮尔逊相关系数和斯皮尔曼相关系数 */public class Correlation implements Algorithm{    @Override    public HashMap<String,String> getArgs() {        return null;    }    @Override    public Dataset<Row> getInputData() {        return null;    }    @Override    public void setOutputData() {    }    @Override    public void setInputData(Dataset<Row> dataset) {    }    @Override    public void compute() {    }}