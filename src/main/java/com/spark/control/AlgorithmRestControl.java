package com.spark.control;

import com.spark.service.WordCountServiceJava;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
public class AlgorithmRestControl {
    @Autowired
    private WordCountServiceJava wordCountServiceJava;

    @RequestMapping(value="/wordCountServiceJava", method = RequestMethod.GET)
    public Map<String, Integer> getWordCountServiceJava() {
        return wordCountServiceJava.run();
    }
}
