import com.sample.dao.IRoleDao;
import com.sample.dao.IUserDao;
import com.sample.domin.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class RoleTests {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IRoleDao roleDao;


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
        roleDao = session.getMapper(IRoleDao.class);
    }

    @Test
    public void testFindAll(){
        List<Role> roles = roleDao.findAll();

        Assert.assertEquals(3, roles.size());

        for(Role role: roles){
            System.out.println("--每个角色的信息--");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }
}
