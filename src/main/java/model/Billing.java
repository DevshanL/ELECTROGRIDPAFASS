package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Billing {
	//DB connection
	
			//establishing connection
				private Connection connect() { 
					
					Connection con = null; 
					try{ 
						Class.forName("com.mysql.jdbc.Driver"); 
				 
						//Provide the correct details: DBServer/DBName, username, password 
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid_paf_project", "root", ""); 
					} catch (Exception e) {
						e.printStackTrace();
					} 
					return con; 
				}
			
				 //Add unit count to the user's account
				public String insertUnitCount(String accno, String uname, String unit, String bmonth, String bamount, String issuedDate  ) { 
							
							String output = ""; 
							
							try { 
								Connection con = connect(); 
								
								if (con == null) {
									return "Error while connecting to the database for inserting."; } 
									
									// create a prepared statement
									String query = " insert into billing_tb(`billID`,`AccountNumber`,`name`,`unitCount`,`month`,`billAmount`,`issuedDate`)" + " values (?, ?, ?, ?, ?,?,?)" ; 
									PreparedStatement preparedStmt = con.prepareStatement(query);
									 
									// binding values
									preparedStmt.setInt(1, 0); 
									preparedStmt.setInt(2, Integer.parseInt(accno)); 
									preparedStmt.setString(3, uname); 
									preparedStmt.setFloat(4, Float.parseFloat(unit)); 
									preparedStmt.setString(5, bmonth); 
									
									
									float no = Float.valueOf(unit.toString());
									String billAmount= String.valueOf(calculateBill(no));
									
									
									preparedStmt.setFloat(6, Float.parseFloat(billAmount)); 
									preparedStmt.setString(7, issuedDate);
									
								
									// execute the statement
									preparedStmt.execute(); 
									con.close(); 
									output = "Bill Details Inserted successfully"; 
							} catch (Exception e) { 
								output = "Error while inserting the details."; 
								System.err.println(e.getMessage()); 
							}
							return output; 
						}  

						

					//Calculate bill amount according to usage of unit
					private float calculateBill(float no) {
						
					float sum=0;
						 if (no <= 54) {
							 return  sum=(float) (no*7.85);
						 }
						 if (54 < no && no <= 81) {
							 
							 return sum= (float) ((54 * 7.85) + ((no - 54) * 10)+ 90);
						 }
						 if (81 < no && no <= 108) {
							 
							 return sum= (float) ((54 * 7.85) + (27 * 10)+ ((no - 81)*27.75) + 480);
						 }
						 
						 if (108 < no && no <= 162) {
							 
							 return sum= (float) ((54 * 7.85) + (27 * 10)+ (27 * 27.75) + ((no - 108)*32) + 480);
						 }
						 
						 if (no >162 )
						     return sum =  (float) ((54 * 7.85) + (27 * 10)+ (27 * 27.75) +  (54*32) + ((no - 162)*45) +540 );
						 
						return sum;
						
						
					}
					

}
