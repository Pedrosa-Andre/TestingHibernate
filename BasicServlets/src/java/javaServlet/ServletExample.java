package javaServlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Andre Matheus
 * // This is my servlet exaple, a litle Java program that works in the server
 * // to receive requests, process them and return responses. In this case my
 * // Servlet manages an online web list.
 */
public class ServletExample extends HttpServlet {
    
    private class Student{
        private String fName;
        private String lName;

        public Student(String fName, String lName) {
            this.fName = fName;
            this.lName = lName;
        }

        public String getfName() {
            return fName;
        }

        public void setfName(String fName) {
            this.fName = fName;
        }

        public String getlName() {
            return lName;
        }

        public void setlName(String lName) {
            this.lName = lName;
        }
        
    }
    protected Set<Student> students = new HashSet<Student>();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * // As a idempotent method the GET method just asks for the students list
     * // without changing it.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) { 
            // The code here prints the response HTML page line by line, 
            // changing the visual elements as nescessary to display the output 
            // in the web page.
            out.println("<!DOCTYPE html>" );
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Students List</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Student form</h2>");
            out.println("<form action=\"ServletExample\" method=\"POST\">");
            out.println("Enter your first name: <input type=\"text\" name=\"firstN\"><br/>");
            out.println("Enter your last name: <input type=\"text\" name=\"lastN\"><br/>");
            out.println("<button onclick=\"submit\"> Submit Name </button>");
            out.println("</form>");
            out.println("<form action=\"ServletExample\" method=\"GET\">");
            out.println("<button onclick=\"submit\"> Get Students List </button>");
            out.println("</form>");
            out.println("<h3>Student list:</h3>");              // This is the
            for (Student stu : students){                       // part of the
                out.println(stu.getfName()+" "+ stu.getlName());// code wich
                out.println("<br/>");                           // displays the
            }                                                   // list.
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * // The POST method here gets the users imput (a student name) and  
     * // processes it adding the names to a student list.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String fName = request.getParameter("firstN"); // user imput
            String lName = request.getParameter("lastN");  // user imput
            out.println("<!DOCTYPE html>" );
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Students List</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Student form</h2>");
            out.println("<form action=\"ServletExample\" method=\"POST\">");
            out.println("Enter your first name: <input type=\"text\" name=\"firstN\"><br/>");
            out.println("Enter your last name: <input type=\"text\" name=\"lastN\"><br/>");
            out.print("<button onclick=\"submit\"> Submit Name </button>");
            // verify if any name is empty (ignoring whitespaces).
            if ("".equals(fName.replaceAll("\\s","")) ||
                    "".equals(lName.replaceAll("\\s",""))){
                out.println(" Missing Name!"); //print out an allert message
            } else {
                // if the names are not empty a student is created and added to 
                // the list.
                Student stu = new Student(fName,lName);
                students.add(stu);
                out.println(" Name Submitted!");
            }
            out.println("</form>");
            out.println("<form action=\"ServletExample\" method=\"GET\">");
            out.println("<button onclick=\"submit\"> Get Students List </button>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
