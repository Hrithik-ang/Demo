package com.hhcds;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register ")
public class Register extends HttpServlet {
	
	private static final String query ="INSERT INTO BOOKDATA(BOOKNAME, BOOKEDITION,BOOKPRICE) VALUES(? , ? , ?)";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		//Get the book info
		String bname=request.getParameter("bname");
		String bedition = request.getParameter("bedition");
	    float bprice = Float.parseFloat(request.getParameter("bprice"));
	    
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");   //Loading the jdbc driver
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//generate the connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","root");   // connection statement
			PreparedStatement ps=con.prepareStatement(query);){
			ps.setString(1, bname);
			ps.setString(2, bedition);
			ps.setFloat(3, bprice);
			int count = ps.executeUpdate();
			if(count>0) {
				pw.println("<h3> Record Is Registered Succcessfully </h3>"); 
			} 
			else 
			{
//				response.sendRedirect("index.html");
				pw.println("<h3> Record Is not Registered Successfully ");
			}
			
		}catch (SQLException e ) {
			e.printStackTrace();
			pw.println(" <h1>"+e.getMessage()+"</h1>");
		}catch (Exception e) {
			e.printStackTrace();
			pw.println(" <h1>"+e.getMessage()+"</h1>");
		}
		pw.println("<a href='booklist' align='center'>Index</a>");
		pw.println("<br>");
		pw.println("<a href='booklist' >Book List</a>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
