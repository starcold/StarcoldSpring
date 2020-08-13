/**
 * author: starcold
 * createTime: 2020/08/10
 * context:
 * updateTime:
 * updateContext:
 */
package myBatis;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class MyBatisTest {
    /**
     * 1 新建一个会话工厂构建器
     * 2 基于配置文件转换成Configuration
     * 3 获取一个会话 在request阶段，存储在ThreadLocal对象里
     * 4 执行查询语句
     */
    @Test
    public void queryTest(){
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream input = getClass().getResourceAsStream("/mybatis-config.xml");
        Properties pro = new Properties();
        //这里设置属性优先级最高
//        pro.setProperty("jdbc.username", "root");
        pro.setProperty("jdbc.username", "xinghan");
        SqlSessionFactory factory = builder.build(input,pro);
        //构建测试环境会话
        SqlSessionFactory testfactory = builder.build(input,"test",pro);
        factory.getConfiguration();
        //打开一个会话
        SqlSession session = factory.openSession();
        Object obj = session.selectOne("myBatis.selectUser",1);
        System.out.println(obj instanceof UserInfo);
    }

    /**
     * @author: starcold
     * @name parseConfiguration
     * @return null
     * @description：
     */
    @Test
    public void parseConfiguration(){
        Configuration config = new Configuration();
        InputStream  inputStream = getClass().getResourceAsStream("/userInfo.xml");
        XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, config, "/userInfo.xml", config.getSqlFragments());
        builder.parse();
        config.getMappedStatement("myBatis.selectUser");
    }
}
