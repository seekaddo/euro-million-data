package com.euroMillio.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOcloudSql {

    public DAOcloudSql() {
    }

    // TODO:
    // The instance connection name can be obtained from the instance overview page in Cloud Console
    // or by running "gcloud sql instances describe <instance> | grep connectionName".
    final private String instanceConnectionName = "steam-glass-209622:europe-west1:euromdb";

    // TODO: fill this in
    // The database to manipulate.
    final private String databaseName = "euromlDB";


    /*
     * Login Details here
     * Username: root
     * Password: 65d5991b
     * */
    final private String username = "root";
    // This is the password that was set via the Cloud Console or empty if never set
    // (not recommended).
    final private String password = "65d5991b";

    //[START doc-example]
    String jdbcUrl = String.format(
            "jdbc:mysql://google/%s?cloudSqlInstance=%s&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false",
            databaseName,
            instanceConnectionName);


    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void connectDB() throws SQLException {

        this.connection = DriverManager.getConnection(this.jdbcUrl, this.username, this.password);


    }

    public List<EuroResults> getAllResutls() throws SQLException {
        List<EuroResults> rsults = new ArrayList<>();


        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from euromlDB");

            while (resultSet.next()) {
                EuroResults euroResults = new EuroResults();
                euroResults.setBalls_drawn(resultSet.getString("balls_drawn"));
                euroResults.setData(resultSet.getString("date"));
                euroResults.setLucky_stars(resultSet.getString("lucky_stars"));
                euroResults.setDrawNr(resultSet.getInt("drawNr"));
                rsults.add(euroResults);
            }
            return rsults;
        }
    }

    public int doInsert(EuroResults result) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String update = String.format("insert into euromlDB " +
                            "(drawNr, date, balls_drawn, lucky_stars)  values (%d, %s, %s, %s)",
                    result.getDrawNr(), result.getData(), result.getBalls_drawn(), result.getLucky_stars());

            return statement.executeUpdate(update);

        }

    }


}
