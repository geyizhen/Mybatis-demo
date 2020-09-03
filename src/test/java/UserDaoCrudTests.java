import com.sample.dao.IUserDao;
import com.sample.domin.AccountUser;
import com.sample.domin.QueryVo;
import com.sample.domin.User;
import com.sun.xml.internal.ws.policy.AssertionSet;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserDaoCrudTests {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

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
        userDao = session.getMapper(IUserDao.class);
    }

    @Test
    public void FindAll(){
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
        System.out.println(users.size());
        //assert accountUsers.size() == 3;
    }

    @Test
    public void testQueryByVo(){
        QueryVo vo = new QueryVo();
        vo.setUsername("%王%");
        vo.setAddress("%南京%");

        List<User> users = userDao.findByVo(vo);

        assert users.size() == 1;
    }


    @Test
    public void testFindOne(){
        User user = userDao.findById(41);
        System.out.println(user);
        assert user.getUsername().equals("张三");
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("华泰");
        user.setAddress("南京市建邺区");
        user.setSex("男");
        user.setBirthday(new Date());

        //执行保存方法
        int id = userDao.saveUser(user);//id为受影响的行数
        System.out.println(user);


        //验证保存结果
        User savedUser = userDao.findById(id);
        System.out.println(savedUser);
        Assert.assertEquals(1, id);
        //assert savedUser.getUsername().equals("华泰");
    }




    @Test
    public void testUpdateUser(){
        int id = 46;

        //根据id查询
        User user = userDao.findById(id);

        //更新操作
        user.setAddress("北京市顺义区");
        int res = userDao.updateUser(user);
        System.out.println(res);

        //验证保存结果
        User savedUser = userDao.findById(id);
        assert savedUser.getAddress().equals("北京市顺义区");
    }

    @Test
    public void testDeleteUser(){
        //执行操作
        int res = userDao.deleteUser(61);

        Assert.assertEquals(1, res);
    }

    @Test
    public void testFindByName(){
        //执行查询一个方法
        List<User> users = userDao.findByName("%王%");

        Assert.assertEquals(2, users.size());

        for(User user: users){
            System.out.println(user);
        }
    }

    @Test
    public void testCount(){
        //执行操作
        int res = userDao.count();

        Assert.assertEquals(14, res);
    }

    @After
    public void tearDown() throws Exception{
        session.commit();

        session.close();
        in.close();
    }
}
