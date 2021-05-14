package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	
	
	public Connection connect() {  
		Connection con = null;            
		
			try          
			{          
				Class.forName("com.mysql.jdbc.Driver");          
				con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb",  "root", "");     
		
	
				//For testing          
				System.out.print("Successfully connected");            
			}         
			catch(Exception e)          
			{          
				e.printStackTrace();                  
			}               
		return con; 
	}
	
	
	public String insertUser(String name,String type, String email, String username, String password)  
	{   
		String output = "";  
	
		try   
		{    
			Connection con = connect();  
		
			if (con == null)    
			{     
				return "Error while connecting to the database for inserting.";    
			}  
			
			// create a prepared statement   
			String query = " insert into user(`userID`,`name`,`type`,`email`,`username`,`password`)"+ " values (?, ?, ?, ?, ?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values    
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, name);    
			preparedStmt.setString(3, type);    
			preparedStmt.setString(4, email);    
			preparedStmt.setString(5, username);
			preparedStmt.setString(6, password);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close();
			
			String newUser = readUsers();    
			output = "{\"status\":\"success\", \"data\": \"" +        
						newUser + "\"}";  
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":         "
					+ "\"Error while inserting the user.\"}";    
			System.err.println(e.getMessage());   
		}
		
		return output;  
	}
	
	public String readUsers()  
	{   
		String output = "";  
	
		try   
		{    
			Connection con = connect();  
		
			if (con == null)    
			{     
				return "Error while connecting to the database for reading.";   
			}  
			
		
		// Prepare the html table to be displayed    
		output = "<table border='1'><tr><th>Name</th>"
				+ "<th>Type</th>"
				+ "<th>email</th>"      
				+ "<th>Username</th>"
				+ "<th>Password</th>"
				+ "<th>Update</th><th>Remove</th></tr>";
		
		String query = "select * from user";    
		Statement stmt = con.createStatement();    
		ResultSet rs = stmt.executeQuery(query);
		
	   // iterate through the rows in the result set    
		while (rs.next())    
		{     
			String userID = Integer.toString(rs.getInt("userID"));     
			String name = rs.getString("name");
			String type = rs.getString("type");     
			String email = rs.getString("email");     
			String username = rs.getString("username");
			String password = rs.getString("password");
					;  
		    // Add into the html table     
			output += "<tr><td><input userID='hidCustomerIDUpdate' "
					+ "name='hidCustomerIDUpdate'          "
					+ "type='hidden' value='" + userID       
					+ "'>" + name + "</td>";     
			output += "<td>" + type + "</td>";     
			output += "<td>" + email + "</td>";     
			output += "<td>" + username + "</td>";
			output += "<td>" + password + "</td>";
			
		    // buttons     
			output += "<td><input name='btnUpdate'          "
					+ "type='button' value='Update'         "
					+ "class='btnUpdate btn btn-secondary'></td>"       
					+ "<td><input name='btnRemove'         "
					+ "type='button' value='Remove'         "
					+ "class='btnRemove btn btn-danger'        "
					+ "data-userid='"       
					+ userID + "'>" + "</td></tr>";    
		}  
		con.close();
		
		// Complete the html table    
		output += "</table>";   
	}  
	catch (Exception e)   
	{    
		output = "Error while reading the users.";    
		System.err.println(e.getMessage());   
	}
	
	return output;  
	}
	
	public String updateUser(String userID, String name, String type, String email, String username, String password)  
	{   
		String output = "";  
	
		try   
		{    
			Connection con = connect();  
		
			if (con == null)    
			{     
				return "Error while connecting to the database for updating.";    }  
			
	   // create a prepared statement    
			String query = "UPDATE user SET name=?,type=?,email=?,username=?,password=?WHERE userID=?";
	   
	   PreparedStatement preparedStmt = con.prepareStatement(query);
	   
	   // binding values    
	   preparedStmt.setString(1, name);    
	   preparedStmt.setString(2, type);    
	   preparedStmt.setString(3, email);    
	   preparedStmt.setString(4, username);
	   preparedStmt.setString(5, password);
	   preparedStmt.setInt(6, Integer.parseInt(userID));
	   
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close();
	   
	   String newUser = readUsers();    
	   output = "{\"status\":\"success\", \"data\": \"" +    
	   newUser + "\"}";   
	   
	}   
		catch (Exception e)   
	{   
			output = "{\"status\":\"error\", \"data\":         "
					+ "\"Error while updating the user.\"}";    
			System.err.println(e.getMessage());   
   	}  
	return output;  
	}
	
	
	public String deleteUser(String userID)  
	{   
		String output = "";
		
		try   
		{    
			Connection con = connect();
			
			if (con == null)    
			{     
				return "Error while connecting to the database for deleting.";    
			}
			
			// create a prepared statement    
			String query = "delete from user where userID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(userID));
			
			// execute the statement    
			preparedStmt.execute();    
			con.close();
			
			String newUser = readUsers();    
			output = "{\"status\":\"success\", \"data\": \"" +        
					newUser + "\"}";   
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":         "
					+ "\"Error while deleting the user.\"}";    
			System.err.println(e.getMessage());   
		}
		
		return output;  
	} 

}
