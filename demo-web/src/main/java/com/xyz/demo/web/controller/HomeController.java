package com.xyz.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * =============================================================================
 * == @author yoze
 * == @date 2018/3/8
 * == @desc
 * =============================================================================
 **/
@Controller
public class HomeController {
    @ResponseBody
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "Hello Jenkins";
    }
}
