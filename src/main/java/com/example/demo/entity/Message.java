package com.example.demo.entity;

import lombok.Data;
import java.util.Date;

/**
 * @author pengyongjie
 * @date 2021/01/30
 */
@Data
public class Message {
    private Long id;
    private String msg;
    private Date time;
}
