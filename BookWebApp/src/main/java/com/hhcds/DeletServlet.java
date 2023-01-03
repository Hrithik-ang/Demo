package com.hhcds;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/deleteurl")
public class DeletServlet extends HttpServlet {
	
	private static final String query ="delete from BOOKDATA where id=?";
	
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
			int count =ps.executeUpdate();
			if(count>0) {
				pw.println("<h3> Record is Delete Successfully </h3>");
			}
			else {
				pw.println("<h3> Record is not deleted Successfully </h3>");
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
