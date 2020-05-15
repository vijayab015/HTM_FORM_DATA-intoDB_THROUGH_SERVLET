package com.register;

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


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public RegisterServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("yourName");
		String city=request.getParameter("yourCity");
		
		out.print(name);
		out.print(city);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/mydb?characterEncoding=utf8","root","********");  
			
			
			PreparedStatement psmt=con.prepareStatement("insert into user (name,city) values (?,?)");
		
			psmt.setString(1,name);
			psmt.setString(2, city);
			
			int res=psmt.executeUpdate();
			
			System.out.println((res>=1)? "data Inserted successfully " :"data not inserted");
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	

}
