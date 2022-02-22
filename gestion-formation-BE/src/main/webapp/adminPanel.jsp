<%-- 
    Document   : welcome
    Created on : 16 févr. 2022, 14:41:32
    Author     : valen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome to adminPanel ${name} !</h1>
        
        <form action="logout" method="POST">
            <input type="submit" value="logout"/>
        </form>
    </body>
</html>
