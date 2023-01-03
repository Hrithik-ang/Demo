package com.hhcds;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {

private static final String query ="SELECT ID, BOOKNAME, BOOKEDITION, BOOKPRICE FROM BOOKDATA where id=?";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		//get the id  of record    for editing purpose 
		int id=Integer.parseInt(request.getParameter("id"));
			    
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");   //Loading the jdbc driver
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//generate the connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","root");    // connection statement
			PreparedStatement ps=con.prepareStatement(query);){
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();	
			rs.next();
			pw.println("<form action='editurl?id="+id+"' method='post'>");
			pw.println("<table>");
			
			pw.println("<tr>");
			pw.println("<td> Book Name </td>");
			pw.println("<td> <input type='text' name='bname'value='"+rs.getString(2)+"'> </td>");
			pw.println("<tr>");
			
			pw.println("<tr>");
			pw.println("<td> Book Edition </td>");
			pw.println("<td> <input type='text' name='bedition'value='"+rs.getString(3)+"'> </td>");
			pw.println("<tr>");
			
			pw.println("<tr>");
			pw.println("<td> Book Price </td>");
			pw.println("<td> <input type='text' name='bprice'value='"+rs.getFloat(4)+"'> </td>");
			pw.println("<tr>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td> <input type='submit' value='Edit' >  </td>");
			pw.println("<td> <input type='reset' value='cancel'>  </td>");
			pw.println("</tr>");
			
			pw.println("</table>");
			pw.println("</form>");
			
		}catch (SQLException e ) {
			e.printStackTrace();
			pw.println(" <h1>"+e.getMessage()+"</h1>");
		}catch (Exception e) {
			e.printStackTrace();
			pw.println(" <h1>"+e.getMessage()+"</h1>");
		}
		pw.println("<a href='booklist' align='center'>Index</a>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
	
	
	

