package com.example.demo.controller;


import com.example.demo.common.utils.JedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengyongjie
 * @date 2021/01/30
 */
@Api(tags = "Redis")
@RestController
@RequestMapping("redis")
public class RedisController {


    @ApiOperation("Redis添加接口")
    @PostMapping("set/id")
    public void setNameToRedis(String id){
        JedisUtil.set(id,id);
    }


    @ApiOperation("Redis删除接口")
    @PostMapping("delete/id")
    public void deleteNameFromRedis(String id) {
        JedisUtil.del(id);
    }


    @ApiOperation("Redis获取接口")
    @GetMapping("get/id")
    public String getNameFromRedis(String id){
        return JedisUtil.get(id);
    }

}
