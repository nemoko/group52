<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Fruits</h1>
        <%! String[] array = new String[] {"apple", "kiwi", "orange"}; %>
        <select>
            <% for (String a : array) { %>
                <option> <%= a %> </option>
            <% } %>
        </select>
    </body>
</html>
