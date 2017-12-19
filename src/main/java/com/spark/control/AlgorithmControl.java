package com.spark.control;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AlgorithmControl {



    //跳转到首页页面
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index() {
        //跳转到 templates 目录下的index.html
        return "index";
    }

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String test() {
        //跳转到 templates 目录下的test.html
        return "test";
    }



}
