package com.atguigu.mangodbtest.repository;

import com.atguigu.mangodbtest.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author:厚积薄发
 * @create:2022-07-15-13:00
 */
public interface UserRepository extends MongoRepository<User,String> {
}
