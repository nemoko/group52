<%-- 
    Document   : userpage
    Created on : 12.03.2012, 10:18:08
    Author     : Mayerhofer
--%>
<jsp:useBean id="user" class="model.User" scope="session"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Userpage</title>
    </head>
    <body>
        <h1>Hello <%=user.getFirstname()%> <%=user.getLastname()%></h1>
        <p>
            What do you want to do?<br/>
            <a href="LoginServlet?action=userdata">Update user data</a><br/>
            <a href="LoginServlet?action=logout">Logout</a>
        </p>
    </body>
</html>
