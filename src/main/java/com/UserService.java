package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

import model.User;

@Path("/Users") 
public class UserService {
	
		User userObj =new User();
		
		//Read users
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readUsers() 
		 { 
			return userObj.readUsers(); 
		 } 
		
		//Insert Users
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertUser( 
			 @FormParam("cusName") String cName, 
			 @FormParam("cusAddress") String cAddress, 
			 @FormParam("cusRegion") String cRegion,
			 @FormParam("cusPostalCode") String cPostalCode,
			 @FormParam("cusContactNo") String cContactNo,
			 @FormParam("cusAccountID") String cAccountID,
			 @FormParam("cusMeterNumber") String cMeterNb,
			 @FormParam("cusLoadType") String cLoadType,
			 @FormParam("cusReadingType") String cReadingType,
			 @FormParam("cusPaymentMethod") String cPaymentType) 
		{ 
				String output = userObj.insertUser(cName, cAddress, cRegion, cPostalCode, cContactNo, cAccountID, cMeterNb, cLoadType, cReadingType, cPaymentType); 
				return output; 
		}
		
		//Update Users
		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateUser(String userData) 
		{ 
			//Convert the input string to a JSON object 
			 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
			 
			//Read the values from the JSON object
			 String cID = userObject.get("cusID").getAsString(); 
			 String cName = userObject.get("cusName").getAsString(); 
			 String cAddress = userObject.get("cusAddress").getAsString(); 
			 String cRegion = userObject.get("cusRegion").getAsString(); 
			 String cPostalCode = userObject.get("cusPostalCode").getAsString(); 
			 String cContactNo = userObject.get("cusContactNo").getAsString();
			 String cAccountID = userObject.get("cusAccountID").getAsString();
			 String cMeterNb = userObject.get("cusMeterNumber").getAsString();
			 String cLoadType = userObject.get("cusLoadType").getAsString();
			 String cReadingType = userObject.get("cusReadingtype").getAsString();
			 String cPaymentType = userObject.get("cusPaymentMethod").getAsString();
			 String output = userObj.updateUser(cID, cName, cAddress, cRegion, cPostalCode, cContactNo, cAccountID, cMeterNb, cLoadType, cReadingType, cPaymentType); 

			 
		return output; 
		}
		
		//Delete Users
		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteUser(String userData) 
		{ 
			//Convert the input string to an XML document
			 Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
			 
			//Read the value from the element <itemID>
			 String cID = doc.select("cusID").text(); 
			 String output = userObj.deleteUser(cID); 
			return output; 
		}
		
		
}
