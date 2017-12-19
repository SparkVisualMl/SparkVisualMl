package com.spark.service;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.spark.sql.SparkSession;

import java.util.Iterator;
import java.util.Properties;

public class SparkJdbcForMySQLService {

    @Autowired
    private transient SparkSession spark1;
    public String getMysqlDF( String ip,  String username, String password,  String port, String dbtable){
        Dataset<Row> jdbcDF = spark1.read().format("jdbc")
                .option("driver", "com.mysql.jdbc.Driver")
                .option("url", "jdbc:mysql://120.76.245.1:3306/test")
                .option("dbtable", "dui_ma")
                .option("user", "root")
                .option("password", "")
                .load();
        Iterator iterator = jdbcDF.javaRDD().collect().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


        return "";
    }
}
