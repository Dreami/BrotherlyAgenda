package brotherpackage;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class taskServlet extends HttpServlet {
    
    private String datePattern = "MM/dd/yyyy";
    private List<Task> taskList = new ArrayList<Task>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession();
        //ObjectMapper mapper = new ObjectMapper();
        
        Cookie[] tasksInCookies = request.getCookies();
        for(Cookie c : tasksInCookies) {
            if(c.getName().equals("task")) {
                String jsonInString = c.getValue();
                
                TypeReference<ArrayList<Task>> listType = new TypeReference<ArrayList<Task>>() {};
                 
                taskList = JsonUtil.toObject(jsonInString, listType);
                //taskList.add(0, element);
                //taskList = JsonConvert.DeserializeObject
                 
                 
            }
        }
        
        if(request.getParameter("createTask") != null) {
            s.setAttribute("savetasktext", "Crear nueva tarea");
            response.sendRedirect("saveTask.jsp");
        } else if (request.getParameter("editTask") != null){
            s.setAttribute("savetasktext", "Modificar tarea");
            response.sendRedirect("saveTask.jsp");
        } else if (request.getParameter("savetask") != null){
            request.getSession().removeAttribute("errorMessage");
            String name, dueDateText, description;
            LocalDate dueDate = null;
            name = request.getParameter("name");
            dueDateText = request.getParameter("duedate");
            description = request.getParameter("description");
            
            
            Pattern p = Pattern.compile("([0-9]{2})/([0-9]{2})/([0-9]{4})");
            Matcher m = p.matcher(dueDateText);
            
            if(m.matches()) {
                
                String[] dateArray = dueDateText.split("/");
                int month, day;
                month = Integer.parseInt(dateArray[0]);
                day = Integer.parseInt(dateArray[1]);
                
                if(!(month > 0 && month < 13)) {
                    attachErrorMsg(request, response, "Los meses deben de estar dentro de un rango de 1 y 12.");
                }
                
                if(!(day > 0 && day < 32)) {
                    attachErrorMsg(request, response, "Los días deben de ser entre 1 y 31.");
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
                dueDate = LocalDate.parse(dueDateText, formatter);
            } else {
                attachErrorMsg(request, response, "El formato de la fecha es incorrecto. Debe seguir 'MM/dd/yyyy'.");
            }
            
            if(name.isEmpty()) {
                attachErrorMsg(request, response, "Es obligatorio llenar el campo de tarea.");
            }
            
            Task task = new Task(name, description, dueDateText);
            taskList.add(task);
            String jsonTask = JsonUtil.toJsonString(taskList);

            Cookie cookie = new Cookie("task", jsonTask);
            response.addCookie(cookie);
            
            request.getSession().removeAttribute("errorMessage");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
            /*
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet taskServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet taskServlet at " + request.getContextPath() + "</h1>");
                out.println("<p>name:" + name + ", duedate:" + dueDate + ", description: " + description + "</p>");
                out.println("</body>");
                out.println("</html>");
            */
            }
        }
    
        private void attachErrorMsg(HttpServletRequest request, HttpServletResponse response, String errMsg) 
        throws ServletException, IOException {
            request.getSession().setAttribute("errorMessage", errMsg);
            response.sendRedirect(request.getHeader("index.jsp"));
        }
    
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
        
}
