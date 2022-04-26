package model;
import java.sql.*;

public class User {
	
		//DB connection
		private Connection connect() {
			Connection con=null;
				
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid","root","admin");
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			return con;
		}
		
		//Insert User
		public String insertUser(
				String cName,
				String cAddress,
				String cRegion,
				String cPostalCode,
				String cContactNo,
				String cAccountID,
				String cMeterNb,
				String cLoadType,
				String cReadingType,
				String cPaymentType) {
			
			String output="";
			
			try {
				Connection con=connect();
				
				if(con ==  null)
				{
					return "Error while inserting to the database for inserting.";
				}
				
				// create a prepared statement
				 String query = " insert into users(`cusID`,"
				 		+ "`cusName`,"
				 		+ "`cusAddress`,"
				 		+ "`cusRegion`,"
				 		+ "`cusPostalCode`,"
				 		+ "`cusContactNo`,"
				 		+ "`cusAccountID`,"
				 		+ "`cusMeterNumber`,"
				 		+ "`cusLoadType`,"
				 		+ "`cusReadingType`,"
				 		+ "`cusPaymentMethod`)"
				 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, cName); 
				 preparedStmt.setString(3, cAddress); 
				 preparedStmt.setString(4, cRegion); 
				 preparedStmt.setInt(5, Integer.parseInt(cPostalCode));
				 preparedStmt.setInt(6, Integer.parseInt(cContactNo));
				 preparedStmt.setString(7, cAccountID);
				 preparedStmt.setInt(8, Integer.parseInt(cMeterNb));
				 preparedStmt.setString(9, cLoadType);
				 preparedStmt.setString(10, cReadingType);
				 preparedStmt.setString(11, cPaymentType);
				 
				 
				 //execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 output="Inserted successfully!";
			}
			catch(Exception e){
				output ="Error while inserting the user.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		//Read Users
		public String readUsers() {
			String output="";
			
			try {
				Connection con=connect();
				
				if(con == null) {
					return "Error while connecting to database for reading items.";
				}
				
				// Prepare the html table to be displayed
				 output = "<table border='1'>"
				 		+ "<tr>"
				 		+ "<th>Customer ID</th>"
				 		+ "<th>Customer Name</th>"
				 		+ "<th>Address</th>" 
				 		+ "<th>Region</th>"
				 		+ "<th>Postal Code</th>"
				 		+ "<th>Contact no</th>"
				 		+ "<th>Account ID</th>"
				 		+ "<th>Meter Number</th>"
				 		+ "<th>Load Type</th>"
				 		+ "<th>Reading Type</th>"
				 		+ "<th>Payment Method</th>"
				 		+ "<th>Update</th><th>Remove</th>"
				 		+ "</tr>";
				 
				 String query = "select * from users"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query);
				 
				 //Iterate through the rows in the result set
				 while(rs.next()) {
					 
					 String cID = Integer.toString(rs.getInt("cusID")); 
					 String cName = rs.getString("cusName"); 
					 String cAddress = rs.getString("cusAddress"); 
					 String cRegion = rs.getString("cusRegion");
					 String cPostalCode = Integer.toString(rs.getInt("cusPostalCode"));
					 String cContactNo = Integer.toString(rs.getInt("cusContactNo")); 
					 String cAccountID = rs.getString("cusAccountID");
					 String cMeterNb = Integer.toString(rs.getInt("cusMeterNumber"));
					 String cLoadType = rs.getString("cusLoadtype");
					 String cReadingType = rs.getString("cusReadingType");
					 String cPaymentType = rs.getString("cusPaymentMethod");
					 
					 // Add into the html table
					 output += "<tr><td>" + cID + "</td>"; 
					 output += "<td>" + cName + "</td>"; 
					 output += "<td>" + cAddress + "</td>"; 
					 output += "<td>" + cRegion + "</td>";
					 output += "<td>" + cPostalCode + "</td>";
					 output += "<td>" + cContactNo + "</td>";
					 output += "<td>" + cAccountID + "</td>";
					 output += "<td>" + cMeterNb + "</td>";
					 output += "<td>" + cLoadType + "</td>";
					 output += "<td>" + cReadingType + "</td>";
					 output += "<td>" + cPaymentType + "</td>";
					 
					
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
					 + "<td><form method='post' action='users.jsp'>"
					 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
					 + "<input name='cusID' type='hidden' value='" + cID 
					 + "'>" + "</form></td></tr>";
				 }
				 
				 con.close();
				 
				 //Complete the html table
				 output += "</table>";
				 
			}
			catch(Exception e) {
				output ="Error while reading the user";
				System.err.println(e.getMessage());
			}
			
			
			return output;
			
		}
		
		//Update Users
		public String updateUser(String cID,
				String cName,
				String cAddress,
				String cRegion,
				String cPostalCode,
				String cContactNo,
				String cAccountID,
				String cMeterNb,
				String cLoadType,
				String cReadingType,
				String cPaymentType) {
			
			String output="";
			
			try {
				Connection con=connect();
				
				if(con ==null) {
					return "Error while connecting to database for updating.";
				}
				
				// create a prepared statement
				 String query = "UPDATE users SET cusName=?,"
				 		+ "cusAddress=?,"
				 		+ "cusRegion=?, "
				 		+ "cusPostalCode=?,"
				 		+ "cusContactNo=?,"
				 		+ "cusAccountID=?,"
				 		+ "cusMeterNumber=?,"
				 		+ "cusLoadType=?,"
				 		+ "cusReadingtype=?,"
				 		+ "cusPaymentMethod=? WHERE cusID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 //preparedStmt.setInt(1, cID); 
				 preparedStmt.setString(1, cName); 
				 preparedStmt.setString(2, cAddress); 
				 preparedStmt.setString(3, cRegion); 
				 preparedStmt.setInt(4, Integer.parseInt(cPostalCode));
				 preparedStmt.setInt(5, Integer.parseInt(cContactNo));
				 preparedStmt.setString(6, cAccountID);
				 preparedStmt.setInt(7, Integer.parseInt(cMeterNb));
				 preparedStmt.setString(8, cLoadType);
				 preparedStmt.setString(9, cReadingType);
				 preparedStmt.setString(10, cPaymentType);
				 preparedStmt.setInt(11, Integer.parseInt(cID));
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 
				 con.close();
				 
				 output = "Updated successfully";
				
			}
			catch(Exception e) {
				output="Error while updating the user.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}

		//Delete User
		public String deleteUser(String cID) {
			String output="";
			
			try {
				Connection con=connect();
				
				if(con == null) {
					return "Error while connecting to database for deleting items.";
				}
				
				// create a prepared statement
				 String query = "delete from users where cusID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(cID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 output = "Deleted successfully";
				
			}
			catch(Exception e) {
				output = "Error while deleting the user."; 
				System.err.println(e.getMessage());
			}
			
			return output;
		}
}
