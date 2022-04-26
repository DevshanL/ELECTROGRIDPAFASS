package model;


import java.sql.*;

public class Card { 
//A common method to connect to the DB

    private Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Provide the correct details: DBServer/DBName, username, password 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/payment", "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public String insertItem(String id, String card, String name, String date,String cvc) {
        String output = "";
        try {
            Connection con = connect();
            if (con == null) {
                return "Error while connecting to the database for inserting.";
            }else{
            // create a prepared statement
            String query = " insert into payments(`id`,`card`,`name`,`date`,`cvc`)"
 + " values(?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query);
            // binding values
            preparedStmt.setInt(1, 0);
            preparedStmt.setString(2, card);
            preparedStmt.setString(3, name);
            preparedStmt.setString(4, date);
            preparedStmt.setString(5, cvc);
            // execute the statement

            preparedStmt.execute();  
            con.close();
             output = "Inserted successfully";
            }
        } catch (Exception e) {
            output = "Error while inserting the Card.";
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String readItems() {
        String output = "";
        try {
            Connection con = connect();
            if (con == null) {
                return "Error while connecting to the database for reading.";
            }
            // Prepare the html table to be displayed
            output = "<table border='1'><tr><th>Card Number</th><th>Card Name</th>"
                    + "<th>Expiry Date</th>"
                    + "<th>Secure Code</th>"
                    + "<th>Update</th><th>Remove</th></tr>";

            String query = "select * from payments";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // iterate through the rows in the result set
            while (rs.next()) {
                String id = Integer.toString(rs.getInt("id"));
                String card = rs.getString("card");
                String name = rs.getString("name");
                String date = rs.getString("date");
                String cvc = rs.getString("cvc");
                // Add into the html table
                output += "<tr><td>" + card + "</td>";
                output += "<td>" + name + "</td>";
                output += "<td>" + date + "</td>";
                output += "<td>" + cvc + "</td>";
                // buttons
                output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
                        + "<td><form method='post' action='Card.jsp'>"
                        + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
                        + "<input name='ID' type='hidden' value='" + id
                        + "'>" + "</form></td></tr>";
            }
            con.close();
            // Complete the html table
            output += "</table>";
        } catch (Exception e) {
            output = "Error while reading the Card Details.";
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String updateItem(String id, String card, String name, String date, String cvc) {
        String output = "";
        try {
            Connection con = connect();
            if (con == null) {
                return "Error while connecting to the database for updating.";
            }
            // create a prepared statement
            String query = "UPDATE payemts SET card=?,name=?,date=?,cvc=? WHERE id =?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query);
            // binding values
            preparedStmt.setString(1, card);
            preparedStmt.setString(2, name);
            preparedStmt.setString(3, date);
            preparedStmt.setString(4, cvc);
            preparedStmt.setInt(5, Integer.parseInt(id));
            // execute the statement
            preparedStmt.execute();
            con.close();
            output = "Updated successfully";
        } catch (Exception e) {
            output = "Error while updating the Card.";
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String deleteItem(String ID) {
        String output = "";
        try {
            Connection con = connect();
            if (con == null) {
                return "Error while connecting to the database for deleting.";
            }
            // create a prepared statement
            String query = "delete from payments where id=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            // binding values
            preparedStmt.setInt(1, Integer.parseInt(ID));
            // execute the statement
            preparedStmt.execute();
            con.close();
            output = "Deleted successfully";
        } catch (Exception e) {
            output = "Error while deleting the Card.";
            System.err.println(e.getMessage());
        }
        return output;
    }

}
