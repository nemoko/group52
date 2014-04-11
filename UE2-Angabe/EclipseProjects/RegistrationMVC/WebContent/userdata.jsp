<%-- 
    Document   : userdata
    Created on : 12.03.2012, 09:50:19
    Author     : Mayerhofer
--%>

<%@page import="model.User.Interest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="model.User" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update user data</title>
    </head>
    <body>
        <h1>Update user data</h1>
        Please update your data.
        <form action="LoginServlet" method="POST">
            <h2>Personal Data</h2>
            <p>
                Firstname: <input type="text" name="firstname" value="<%=user.getFirstname()%>" /><br/>
                Lastname: <input type="text" name="lastname" value="<%=user.getLastname()%>" /><br/>
            </p>
            <h2>Login Data</h2>
            <p>
                Username: <input type="text" name="username" value="<%=user.getUsername()%>" disabled="disabled"/><br/>
                Password: <input type="text" name="password" value="<%=user.getPassword()%>" /><br/>
            </p>
            <h2>Interests</h2>
            <p>
                <input type="checkbox" name ="interests" value ="webEngineering" 
                    <% if (user.getInterests().contains(model.User.Interest.WEBENINEERING)) { %>
                        checked="checked"
                    <% } %>
                />Web Engineering<br/>
                <input type="checkbox" name ="interests" value ="modelEngineering"
                    <% if (user.getInterests().contains(model.User.Interest.MODELENGINEERING)) { %>
                        checked="checked"
                    <% } %>
                />Model Engineering<br/>
                <input type="checkbox" name ="interests" value ="semanticWeb"
                    <% if (user.getInterests().contains(model.User.Interest.SEMANTICWEB)) { %>
                        checked="checked"
                    <% } %>
                />Semantic Web<br/>
                <input type="checkbox" name ="interests" value ="objectOrientedModeling"
                    <% if (user.getInterests().contains(model.User.Interest.OBJECTORIENTEDMODELING)) { %>
                        checked="checked"
                    <% } %>
                />Object Oriented Modeling<br/>
                <input type="checkbox" name ="interests" value ="businessInformatics"
                    <% if (user.getInterests().contains(model.User.Interest.BUSINESSINFORMATICS)) { %>
                        checked="checked"
                    <% } %>
                />Business Informatics<br/>
            </p>
            <input type="submit" value="Submit" name="submit" />
        </form>
    </body>
</html>
