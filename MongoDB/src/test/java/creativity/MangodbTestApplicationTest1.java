package creativity;

import com.atguigu.mangodbtest.entity.User;
import com.atguigu.mangodbtest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author:厚积薄发
 * @create:2022-07-15-13:01
 */
public class MangodbTestApplicationTest1 {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll(){
        List<User> all = userRepository.findAll();
        System.out.println(all);
    }

}
