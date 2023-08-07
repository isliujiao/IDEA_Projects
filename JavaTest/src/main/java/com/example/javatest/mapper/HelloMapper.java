package com.example.javatest.mapper;


import com.example.javatest.domain.HelloDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liujiao
 * @date 2023/7/17 9:54
 */
@Mapper
public interface HelloMapper {

    List<HelloDO> selectList();
}
