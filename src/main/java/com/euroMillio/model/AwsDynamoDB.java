package com.euroMillio.model;

/*
* MIT License

Copyright (c) 2018 Dennis Addo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import org.json.JSONArray;

import javax.json.JsonArray;

/**
 *
 * This contains are the necessary methods needed to communicate/manipulate with amazon DynamoDB instance.
 * This is a simple CRUD for the dynamoDB
 *
 * @author Dennis Addo
 * @Date 22 July, 2018
 * */
public class AwsDynamoDB {


    final static String tableName = "euro-million-data";

    /*
     *  The credentials will be kept in heroku env.
     *
     *  The two class instance is used to provide simplicity in most use cases
     *  of the CRUD function but in most cases AmazonDynamoDB will be enough.
     */

    static AmazonDynamoDB dynamoDB;
    static DynamoDB dynamoDBClient;


    /**
     * The only information needed to create a client are security credentials
     * consisting of the AWS Access Key ID and Secret Access Key. All other
     * configuration, such as the service endpoints, are performed
     * automatically. Client parameters, such as proxies, can be specified in an
     * optional ClientConfiguration object when constructing a client.
     *
     * @see com.amazonaws.auth.BasicAWSCredentials
     * @see com.amazonaws.ClientConfiguration
     */
    public static void init() throws Exception {
        /*
         * The ProfileCredentialsProvider will return your [default]
         * credential profile by reading from the credentials file located at
         * (~/.aws/credentials).
         */
        ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (~/.aws/credentials), and is in valid format.",
                    e);
        }
        dynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion("us-west-2")
                .build();
        dynamoDBClient = new DynamoDB(dynamoDB);
    }


    public static List<String> getAllItemsFromTable(){

       return dynamoDB.scan(new ScanRequest().withTableName(tableName)).
               getItems().stream().map(e-> ItemUtils.toItem(e).toJSON()).
               collect(Collectors.toList());
    }


    public static Item newItem(EuroResults euroResults) {
        Item item = new Item();
        JSONArray list = new JSONArray();
        //This is needed to help order the Json elements. because JSONObject is a unorder map.
        HashMap<String,String[]> objt = new LinkedHashMap<>();
        int baln = euroResults.getBalls_drawn().size();
        objt.put("balls_drawn", euroResults.getBalls_drawn().toArray(new String[baln]));
        baln = euroResults.getLucky_stars().size();
        objt.put("lucky_stars", euroResults.getLucky_stars().toArray(new String[baln]));

        list.put(objt);

         return item.withPrimaryKey("drawNr",euroResults.getDrawNr(),"date", euroResults.getData()).
        withJSON("data", Stream.of(list).map(JSONArray::toString).collect(Collectors.joining(",")));

    }


    public static void doPutItem(EuroResults results){

        Table table = dynamoDBClient.getTable(tableName);

        PutItemOutcome putItemOutcome = table.putItem(newItem(results));
        System.out.println("PutItemOutcome: "+ putItemOutcome.toString());

    }


}
