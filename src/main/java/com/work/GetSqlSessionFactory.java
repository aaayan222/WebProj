package com.work;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class GetSqlSessionFactory {

    private static SqlSessionFactory sqlSessionFactory;

    private GetSqlSessionFactory(){

    }

    /**
     * 使用同步锁
     * @return sql session 工厂
     */
    synchronized public static SqlSessionFactory getSqlSessionFactory(){
        if (sqlSessionFactory == null){
            //获取资源文件流
            String resorce = "mybatis-config.xml";
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(resorce);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        return sqlSessionFactory;
    }

}
