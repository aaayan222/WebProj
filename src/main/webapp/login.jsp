<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/11/23
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript">
        function changeCode(){
            //得到图片元素
            var img = document.getElementsByTagName("img")[0];
            //img.setAttribute("src","/HttpServlet/ServletDemo04");//xml dom 语法
            // 给URL传递参数可以清空浏览器的缓存，让浏览器认为这是一个新的请求
            img.src = "/DrawImage?time="+new Date().getTime();
        }
    </script>
    <style type="text/css">
        #msg1 {color: red;}
    </style>
</head>
<body>
<form action="LoginServlet" method="post">
    用户名：<input type="text" name="userName"/></br>
    密码：<input type="password" name="passWord"/></br>
    验证码：<input type="text" name="code"/>
    <img src="/DrawImage" id="verify" onclick="changeCode()"/>
    <a href="javascript:changeCode()">看不清换一张</a></br>
    <input type="submit" value="登录"/>
    <span id="msg1"><c:out value="${requestScope.msg}"></c:out></span></br>
</form>
</body>
</html>
