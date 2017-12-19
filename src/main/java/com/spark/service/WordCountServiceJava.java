package com.spark.service;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scala.Tuple2;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class WordCountServiceJava implements Serializable {
    private static final Pattern SPACE = Pattern.compile(",");

    @Autowired
    private transient JavaSparkContext sc;
    @Autowired
    private transient SparkSession spark;
    private Logger logger = LoggerFactory.getLogger(WordCountServiceJava.class);
    public Map<String, Integer> run(String fileAddress) {
        Map<String, Integer> result = new HashMap<>();
        JavaRDD<String> lines = spark.read().textFile(fileAddress).toJavaRDD();
        logger.warn("spark info----------"+spark.sparkContext().getConf().get("spark.driver.allowMultipleContexts"));
        lines.map(new Function<String, String>() {
            @Override
            public String call(String s) throws Exception {

                return s;
            }
        });


        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(SPACE.split(s)).iterator();
            }
        });

        JavaPairRDD<String, Integer> ones = words.mapToPair(new PairFunction<String, String, Integer>() {

            private static final long serialVersionUID = 1L;

            public Tuple2<String, Integer> call(String s) {
                return new Tuple2<String, Integer>(s, 1);
            }
        });

        JavaPairRDD<String, Integer> counts = ones.reduceByKey(new Function2<Integer, Integer, Integer>() {

            private static final long serialVersionUID = 1L;

            public Integer call(Integer i1, Integer i2) {
                return i1 + i2;
            }
        });

        List<Tuple2<String, Integer>> output = counts.collect();
        for (Tuple2<String, Integer> tuple : output) {
            result.put(tuple._1(),tuple._2());
        }
        return result;

    }
}