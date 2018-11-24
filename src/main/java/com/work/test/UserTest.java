package com.work.test;

import com.work.mapper.UserMapper;
import com.work.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class UserTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            // 通过Resources工具类将mybatis-config。xml配置文件读入Reader
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }


    @Test
    public void testSelectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 通过SqlSession的selectList方法查找到DataMapper.xml中id=“selectAll”的方法，执行SQL
            List<User> dataList = sqlSession.selectList("selectAll");
            printDataList(dataList);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testFindUserName() {
        init();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
            //User user = usermapper.selectByPrimaryKey(123);
            User data = sqlSession.selectOne("findUserName","qwe");
            System.out.print(data.getUsername()+"\t");
            System.out.print(data.getPassword()+"\n");
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testSelectById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
            //User user = usermapper.selectByPrimaryKey(123);
            User data = sqlSession.selectOne("selectByPrimaryKey",123);
            System.out.print(data.getUserid()+"\t");
            System.out.print(data.getUsername()+"\t");
            System.out.print(data.getPassword()+"\n");
        } finally {
            sqlSession.close();
        }
    }


    private void printDataList(List<User> dataList) {
        for (User data : dataList) {
            System.out.printf("%-4s%4s\n", data.getUserid(), data.getUsername());
        }
    }
}
