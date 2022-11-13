package com.bobo.controller;

import com.bobo.pojo.User;
import com.bobo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    /*@GetMapping("/index")
    @ResponseBody
    public String index(){
        System.out.println("hello....");
        return "success";
    }*/

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/{path}")
    public String base(@PathVariable String path){
        return path;
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/user/addUser")
    public String addUser(User user){
        userService.addUser(user);
        return "success";
    }

    @RequestMapping("user/findAll")
    public String queryAll(Model model){
        List<User> users = userService.queryAll();
        model.addAttribute("list",users);
        return "user";
    }
    @RequestMapping("user/deleteById")
    public String deleteById(Integer userid){
        userService.deletedById(userid);
        return "success";
    }
}
