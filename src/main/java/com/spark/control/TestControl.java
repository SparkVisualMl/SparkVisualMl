package com.spark.control;

import org.apache.spark.SparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
public class TestControl {
    @Autowired
    private SparkContext sc;
    @Value("${db_url}")
    private String db_url;




    @RequestMapping(value="/list" ,method= RequestMethod.GET)
    public String getAccounts(){
        return "test";
    }
    @RequestMapping(value = "/dburl")
    public String geturl(){
        return db_url;
    }
    @RequestMapping(value="/sc" ,method= RequestMethod.GET)
    public SparkContext geSc(){
        return sc;
    }


    @RequestMapping(value="/name" ,method= RequestMethod.GET)
    public String geName(){
        return "name";
    }


    /**
     * 获取前端给出的请求参数
     */

    @RequestMapping(value="/hello/{id}",method= RequestMethod.GET)
    public String sayHello(@PathVariable("id") Integer id){
        return "id:"+id;
    }

    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String sayHello1(@RequestParam("id") Integer id){
        return "id:"+id;
    }
    @RequestMapping(value="/hello1",method= RequestMethod.GET)
    public String sayHello(@RequestParam("id") Integer id,@RequestParam("name") String name){
        return "id:"+id+ " name:"+name;
    }


}
