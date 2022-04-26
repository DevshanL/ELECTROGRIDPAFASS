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

import model.Inquiry;

@Path("/Inquiry")
public class InquiryService {
	
	Inquiry comObj = new Inquiry();
	
	//insert Complain details
	@POST
	@Path("/AddInquiry")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertComplain( 
			 @FormParam("Description") String Description, 
				@FormParam("complainDate") String comdate )
			
	{
		String output = comObj.insertComplain(Description, comdate);
		System.out.println(output);
		return output;
		
	
	}
	


	//Read Complain details
		@GET
		@Path("/ReadInquiry")
		@Produces(MediaType.TEXT_HTML)
		public String readComplain() 
		{
			return comObj.readComplain(); 
		}

		
		//Update Complain 
		@PUT
		@Path("/UpdateInquiry")
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN)
		public String updateComplain(String form2Data)
		{
			//Convert the input string to a JSON object 
			JsonObject comObj2 = new JsonParser().parse(form2Data).getAsJsonObject(); 
			
			//Read the values from the JSON object
			String Description = comObj2.get("Description").getAsString();
			String Complain_id  = comObj2.get("Complain_id").getAsString(); 

			String output = comObj.updateComplain(Description, Complain_id );
			
			
			return output;
		}
		
		//delete object details
		@DELETE
		@Path("/DeleteInquiry")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteCom(String form1Data)
		{
			//Convert to XML document
			Document docum = Jsoup.parse(form1Data, "", Parser.xmlParser());
			
			String comID = docum.select("comID").text();
			String output = comObj.deleteComplain(comID);
			
			return output;
		}
		
		
	
	
}

