package com.euroMillio.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A sample app that connects to a Cloud SQL instance and lists all available tables in a database.
 */
public class ListTables {
    public static void main(String[] args) throws IOException, SQLException {
        // TODO: fill this in
        // The instance connection name can be obtained from the instance overview page in Cloud Console
        // or by running "gcloud sql instances describe <instance> | grep connectionName".
        String instanceConnectionName = "steam-glass-209622:europe-west1:euromdb";

        // TODO: fill this in
        // The database from which to list tables.
        String databaseName = "euromlDB";

        String username = "root";

        // TODO: fill this in
        // This is the password that was set via the Cloud Console or empty if never set
        // (not recommended).
            String password = "65d5991b";

        if (instanceConnectionName.equals("<insert_connection_name>")) {
            System.err.println("Please update the sample to specify the instance connection name.");
            System.exit(1);
        }

        if (password.equals("<insert_password>")) {
            System.err.println("Please update the sample to specify the mysql password.");
            System.exit(1);
        }

        //[START doc-example]
        String jdbcUrl = String.format(
                "jdbc:mysql://google/%s?cloudSqlInstance=%s&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
                databaseName,
                instanceConnectionName);

        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        //[END doc-example]
        /*
        * int resultSet = statement.executeUpdate("create table euroresults\n" +
                    "(\n" +
                    "  drawNr      int         not null,\n" +
                    "  date        varchar(30) not null,\n" +
                    "  balls_drawn varchar(20) not null,\n" +
                    "  lucky_stars varchar(5)  not null,\n" +
                    "  constraint euroresults_date_uindex\n" +
                    "  unique (date),\n" +
                    "  constraint euroresults_drawNr_uindex\n" +
                    "  unique (drawNr),CONSTRAINT PK_Person PRIMARY KEY (drawNr,date)\n" +
                    ");");*/

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("show  tables ");
            System.out.println(resultSet);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        }
    }
}