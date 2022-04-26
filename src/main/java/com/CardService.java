package com;

import model.Card;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import java.net.URI;
import javax.ws.rs.core.Response;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Card")
public class CardService {

    Card itemObj = new Card();

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String readItems() {
        return itemObj.readItems();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertItem(@FormParam("id") String id,
            @FormParam("card") String card,
            @FormParam("name") String name,
            @FormParam("date") String date,
            @FormParam("cvc") String cvc) 
    {
    String output = itemObj.insertItem(id,card,name,date,cvc);
        return Response.temporaryRedirect(URI.create("../Card.jsp")).build();
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateItem(String itemData) {
//Convert the input string to a JSON object 
        JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
//Read the values from the JSON object
        String id = itemObject.get("id").getAsString();
        String card = itemObject.get("card").getAsString();
        String name = itemObject.get("name").getAsString();
        String date = itemObject.get("date").getAsString();
        String cvc = itemObject.get("cvc").getAsString();
        String output = itemObj.updateItem(id, card, name, date, cvc);
        return output;
    }

    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteItem(String itemData) {
//Convert the input string to an XML document
        Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

//Read the value from the element <itemID>
        String itemID = doc.select("ID").text();
        String output = itemObj.deleteItem(itemID);
        return output;
    }

}
