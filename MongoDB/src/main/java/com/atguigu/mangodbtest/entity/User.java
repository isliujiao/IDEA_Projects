package com.atguigu.mangodbtest.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author:厚积薄发
 * @create:2022-07-14-22:30
 */
@Data
@Document("User")
public class User {

    @Id
    private String id;
    private String name;
    private Integer age;
    private String email;
    private String createDate;
}
