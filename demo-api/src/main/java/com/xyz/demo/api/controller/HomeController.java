package com.xyz.demo.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * =============================================================================
 * == @author yoze
 * == @date 2018/3/8
 * == @desc
 * =============================================================================
 **/

@RestController
public class HomeController {

    @RequestMapping("/index")
    public Map<String, Object> index() {
        Map<String, Object> result = new HashMap<>();
        result.put("id", 1);
        result.put("name", "xiao-ming");
        return result;
    }
}
