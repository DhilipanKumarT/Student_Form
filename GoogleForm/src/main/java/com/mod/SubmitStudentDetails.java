package com.mod;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubmitStudentDetails")
public class SubmitStudentDetails extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Name = request.getParameter("Name");
        String Email = request.getParameter("Email");
        String MobileNo = request.getParameter("Mobile");
        String DOB= request.getParameter("DOB");
        String Address = request.getParameter("Address");
        String UGCourse = request.getParameter("UGCourse");
        String Dept = request.getParameter("Dept");
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GoogleForm", "root", "root");
            String sql = "INSERT INTO studdetails (Name, Email,MobileNo, DOB, Address,UGCourse,Dept) VALUES (?, ?, ?, ?, ? ,? ,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Name);
            stmt.setString(2, Email);
            stmt.setString(3, MobileNo);
            stmt.setString(4, DOB);
            stmt.setString(5, Address);
            stmt.setString(6, UGCourse);
            stmt.setString(7, Dept);
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                PrintWriter out = response.getWriter();
                out.println("<html><body><h1>Student details submitted successfully!</h1></body></html>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
