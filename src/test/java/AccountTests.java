import com.sample.dao.IAccountDao;
import com.sample.dao.IUserDao;
import com.sample.domin.AccountUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountTests {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IAccountDao accountDao;

    @Before
    public void setUp() throws Exception {
        // 读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 创建 SqlSession 工厂对象
        factory = builder.build(in);
        // 创建 SqlSession 对象
        session = factory.openSession();
        // 创建 Dao 的代理对象
        accountDao = session.getMapper(IAccountDao.class);
    }

    @Test
    public void testFindAll(){
        List<AccountUser> accountUsers = accountDao.findAll();
        for (AccountUser au : accountUsers){
            System.out.println(au);
        }
        //System.out.println(accountUsers.size());
        assert accountUsers.size() == 3;
    }






}
