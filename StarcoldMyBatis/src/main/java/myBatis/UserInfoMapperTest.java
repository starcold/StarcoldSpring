package myBatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * author: starcold
 * createTime: 2020/8/11 16:38
 * context: UserInfoMapperTest
 * updateTime:
 * updateContext:
 */
public class UserInfoMapperTest {
    @Test
    public void getUserInfo(){
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream input = getClass().getResourceAsStream("/mybatis-config.xml");
        Properties pro = new Properties();
        pro.setProperty("jdbc.username", "root");
        SqlSessionFactory factory = builder.build(input, pro);
        UserInfoMapper mapper = factory.openSession().getMapper(UserInfoMapper.class);
        //getUser
        UserInfo userInfo = mapper.getUser(1);
        Assert.assertNotNull(userInfo);
        System.out.println(userInfo.getNickName());
        //selectUser
        UserInfo info = mapper.selectUser(userInfo,userInfo.getPassword());
        System.out.println(info.getUserName());
        //getUsersByIds
        int []ids = {1,2};
        List<UserInfo> list2 = mapper.getUserByIds(ids);
        System.out.println(list2.get(0).getNickName());
    }
}
