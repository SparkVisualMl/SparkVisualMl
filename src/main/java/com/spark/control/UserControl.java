package com.spark.control;

import com.spark.model.User;
import com.spark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping(value = "/user")
@RestController
public class UserControl {

    @Autowired
    UserRepository userRepository;
    @RequestMapping(value = "/q1",method = RequestMethod.GET)
    public List<User> q1(@RequestParam("address") String address) {
        List<User> user = userRepository.findByAddress(address);
        return user;
    }

    @RequestMapping(value = "/q2",method = RequestMethod.GET)
    public List<User> q2(@RequestParam("address") String address) {
        List<User> user = userRepository.findByAddress(address);
        return user;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> list(String address) {
        List<User> user = userRepository.findAll();
        return user;
    }
}
