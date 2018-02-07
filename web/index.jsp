<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession s = request.getSession();
    String greeting = "Tu alma esta libre";
    String statusText = "No tienes tareas";
    if(s.getAttribute("taskList") != null) {
        greeting = s.getAttribute("greeting").toString();
        statusText = s.getAttribute("statusText").toString();
    }
%>

<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="mainstyle.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Brotherly Web Agenda</title>
    </head>
    <body>
        <div id="main">
            <div id="greeting" class="col-md-6">
                <h1><%=greeting%></h1>
            </div>
            <div id="newTask" class="col-md-2 col-md-offset-4">
                <a href="saveTask.jsp">+ New task</a>
            </div>
        </div>
        
        <div id="statusText">
            <h3><%=statusText%></h3>
        </div>
        <form>
            <div id="taskList">
            
            </div>
        </form>
    </body>
</html>
