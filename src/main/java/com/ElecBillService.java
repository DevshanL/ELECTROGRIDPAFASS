package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.ElecBill;



@Path("/ElecBill")
public class ElecBillService {
	
ElecBill billobj = new ElecBill();
	
    
    //Add Electric Bill Details
	@POST
	@Path("/AddElecBill")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUnitCount(@FormParam("AccountNumber") String AccountNumber, 
								  @FormParam("name") String name, 
								  @FormParam("unitCount") String unitCount, 
								  @FormParam("month") String month,
								  @FormParam("bamount") String bamount ,
								  @FormParam("issuedDate") String issuedDate)
								  {
								 
		
		
		String output = billobj.insertUnitCount(AccountNumber, name, unitCount, month,bamount,issuedDate); 
		
		return output; 
	}
	
	
	
	    //Read All Electric Billing Details
		@GET
		@Path("/AllElecBills")
		@Produces(MediaType.TEXT_HTML)
		public String readUnitCount() {
			return billobj.readUnitCount();
		}
		
		
		
		//Delete All Electric Billing Details
		@DELETE
		@Path("/DeleteElecBill") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteBilling(String form1Data) 
		{ 
			
			
		 //Convert to XML Document
		 Document docu = Jsoup.parse(form1Data, "", Parser.xmlParser()); 
		 
		 String billID = docu.select("billID").text(); 
		 String output = billobj.deleteBilling(billID); 
		
		 return output; 
		}
		
		
		//Updating Electric Billing Details
		@PUT
		@Path("/UpdateElecBill") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateBill(String form1Data) 
		{ 
			
		//Convert form data  to a JSON object 
			
		 JsonObject billobj1 = new JsonParser().parse(form1Data).getAsJsonObject(); 
		 
		 
		 
		//Read the values from the JSON object
		 String billID = billobj1.get("billID").getAsString();
		 String AccountNumber = billobj1.get("AccountNumber").getAsString(); 
		 String name = billobj1.get("name").getAsString(); 
		 String unitCount = billobj1.get("unitCount").getAsString();
		 String month = billobj1.get("month").getAsString(); 
		 float billAmount = billobj1.get("billAmount").getAsFloat();
		 String issuedDate = billobj1.get("issuedDate").getAsString();
		
		 
		 
		 String out = billobj.updateBill( billID,AccountNumber, name, unitCount, month,billAmount,issuedDate);
		 return out;
		}
	
	

}
