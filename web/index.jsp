<%@page import="java.util.List"%>
<%@page import="brotherpackage.Task"%>
<%@page contentType='text/html' pageEncoding='UTF-8'%>
<!DOCTYPE html>
<%
    HttpSession s = request.getSession();
    String greeting = "Tu alma esta libre";
    String statusText = "No tienes tareas";
    
    Cookie[] cookies = request.getCookies();
%>

<html>
    <head>
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
        <link rel='stylesheet' type='text/css' href='mainstyle.css'>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
        <title>Brotherly Web Agenda</title>
    </head>
    <body>
        <form name='mainForm' method='POST' action='taskServlet'>
        <div id='main'>
            <div id='greeting' class='col-md-6'>
                <h1><%=greeting%></h1>
            </div>
            <div id='newTask' class='col-md-2 col-md-offset-4'>
                <button name='createTask' type='submit' class='btn btn-default'>+ New task</button>
            </div>
        </div>
        
        <div id='statusText'>
            <h3><%=statusText%></h3>
        </div>
            <div id='taskList'>
                <%
                    if(cookies != null) {
                        for (int i=0; i<cookies.length; i++) {
                          out.println(cookies[i].getName()+":\t"+
                            cookies[i].getValue()+"<BR>");

                        }
                    }
                %>
            </div>
        </form>
    </body>
</html>
