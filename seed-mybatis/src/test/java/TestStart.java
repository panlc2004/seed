import com.czy.seed.mybatis.MybatisStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by panlc on 2017-03-13.
 */
@SpringBootTest(classes = MybatisStarter.class)
@RunWith(SpringRunner.class)
public class TestStart {

    @Test
    public void test() {
        System.out.println(123);
    }
}
