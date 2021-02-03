package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

/**
 * @author pengyongjie
 * @date 2021/01/30
 */
@Api(tags = "kafka")
@RestController
@RequestMapping("kafka")
public class KafkaController {


    @Autowired
    private KafkaTemplate kafkaTemplate;


    @ApiOperation("生产者生成信息接口")
    @GetMapping("send")
    public void sentMsg(String name){
        Message message = new Message();
        message.setId(1l);
        message.setMsg(name);
        message.setTime(new Date());
        kafkaTemplate.send("topic-demo",JSON.toJSONString(message));
    }
}
