<%@page import="java.util.GregorianCalendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% GregorianCalendar clock = new GregorianCalendar(); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clock</title>
    </head>
    <body>
        The current time is <%= clock.getTime() %> <br/>
        The server's time zone is <%= clock.getTimeZone() %>        
    </body>
</html>
