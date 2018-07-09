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

    public static EuroResults getLastElement(Connection connection) throws Exception {
        EuroResults p = new EuroResults();
        try {
            PreparedStatement ps = connection
                    .prepareStatement("SELECT *\n" +
                            "FROM euroresults\n" +
                            "ORDER BY drawNr DESC\n" +
                            "LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p.setDrawNr(rs.getLong("drawNr"));

                p.setBalls_drawn(rs.getString("balls_drawn"));
                p.setData(rs.getString("date"));
            }
            return p;
        } catch (Exception e) {
            throw e;
        }
    }
}


