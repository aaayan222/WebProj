package com.work.servlet;

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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    User user;
    private String username;
    private String password;
    private String code;
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public void init() {
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
    public void testFindUserName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
            //User user = usermapper.selectByPrimaryKey(123);
            User data = sqlSession.selectOne("findUserName",username);
            System.out.print(data.getUsername()+"\t");
            System.out.print(data.getPassword()+"\n");
        } finally {
            sqlSession.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            username = request.getParameter("userName");
            password = request.getParameter("passWord");
            code = request.getParameter("code");
            testFindUserName();
            //user = sqlSession.selectOne("findUserName", username);
            String code_text = (String) request.getSession().getAttribute("text");
            if (!code_text.equalsIgnoreCase(code)) {
                request.setAttribute("username", username);
                request.setAttribute("msg", "验证码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            /*if(user.getPassword() == null) {
                request.setAttribute("username", username);
                request.setAttribute("msg", "用户不存在");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }*/
            else {
                if (user.getPassword() != null && !password.equals(user.getPassword())) {
                    request.setAttribute("username", username);
                    request.setAttribute("msg", "用户名或者密码错误");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }
                else if (password.equals(user.getPassword())) {
                    request.setAttribute("msg", "用户：" + username + ",登陆成功");
                    request.getRequestDispatcher("/welcome.jsp").forward(request, response);
                    //response.setHeader("Refresh","1;url=welcome.jsp");
                }
            }

        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
    }
}
