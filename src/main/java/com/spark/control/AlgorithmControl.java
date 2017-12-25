package com.spark.control;

import com.spark.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.UnknownHostException;


@Controller
public class AlgorithmControl {

    private Logger logger = LoggerFactory.getLogger(AlgorithmControl.class);

    //跳转到首页页面
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index(Model model) {
        //跳转到 templates 目录下的index.html

        try{
            String ip = IPUtil.getLocalIpAddress();
            model.addAttribute("ip",ip);
        }catch (UnknownHostException e){
            model.addAttribute("ip","error");
            logger.error(e.getMessage());
        }
        return "index";
    }

    @RequestMapping(value="/aigregister", method = RequestMethod.GET)
    public String aigregister() {
        //跳转到 templates 目录下的aigregister.html
        return "aigregister";
    }


    @RequestMapping(value="/datamanager", method = RequestMethod.GET)
    public String datamanager() {
        //跳转到 templates 目录下的test.html
        return "datamanager";
    }
    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String test() {
        //跳转到 templates 目录下的test.html
        return "aigregister";
    }

    @RequestMapping(value="/jsplumb", method = RequestMethod.GET)
    public String jsplumb() {
        //跳转到 templates 目录下的test.html
        return "jsplumbtest";
    }



}
