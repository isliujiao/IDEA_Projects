package creativity;

import com.atguigu.mangodbtest.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
class MangodbTestApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    //添加操作
    @Test
    void create() {
        User user = new User();
        user.setAge(20);
        user.setName("test");
        user.setEmail("4932200@qq.com");
        User user1 = mongoTemplate.insert(user);
        System.out.println(user1);
    }

    //查询表中所以记录
    @Test
    void findAll(){
        List<User> all = mongoTemplate.findAll(User.class);
        System.out.println(all);
    }
}
