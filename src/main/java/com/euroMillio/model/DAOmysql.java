package com.euroMillio.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOmysql {
    public Connection getConnection() throws Exception {
        try {
            //mysql://b5dded0c581dec:65d5991b@eu-cdbr-west-02.cleardb.net/heroku_71edc9fe3ad405e?reconnect=true
            String connectionURL = "jdbc:mysql://eu-cdbr-west-02.cleardb.net:3306/heroku_71edc9fe3ad405e?rewriteBatchedStatements=true";
            Connection connection = null;
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "b5dded0c581dec", "65d5991b");
            return connection;
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public static ArrayList<Person> getAllUsers(Connection connection) throws Exception {
        ArrayList<Person> userList = new ArrayList<Person>();
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM person");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Person uservo = new Person();
                uservo.setName(rs.getString("name"));
                uservo.setAge(rs.getInt("age"));
                uservo.setLocation(rs.getString("location"));
                userList.add(uservo);
            }
            return userList;
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean doInsert(Connection connection, List<EuroResults> eRts) throws Exception{
        boolean status = false;
        try {
            for (EuroResults elm: eRts){
                // the mysql insert statement
                String query = " insert into euroresults (drawNr, date, balls_drawn, lucky_stars)"
                        + " values (?, ?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setLong(1, elm.getDrawNr());
                preparedStmt.setString(2, elm.getData());
                preparedStmt.setString(3, elm.getBalls_drawn());
                preparedStmt.setString(4, elm.getLucky_stars());

                // execute the preparedstatement
                status = preparedStmt.execute();
            }
            return status;
        } catch (Exception e) {
            throw e;
        }
    }

    public static Person getLastElement(Connection connection) throws Exception {
        Person p = new Person();
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT *\n" +
                            "FROM person\n" +
                            "ORDER BY name DESC\n" +
                            "LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p.setName(rs.getString("name"));

                p.setAge(rs.getInt("age"));
                p.setLocation(rs.getString("location"));
            }
            return p;
        } catch (Exception e) {
            throw e;
        }
    }
}


