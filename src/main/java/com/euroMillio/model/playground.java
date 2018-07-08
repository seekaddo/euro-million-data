package com.euroMillio.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.euroMillio.model.DAOmysql.*;
import static com.euroMillio.model.DAOmysql.doInsert;
import static com.euroMillio.model.commons.*;
import static com.euroMillio.model.commons.getFreidaysBetwn2004_und2010;
import static com.euroMillio.model.commons.getRemoteEuroResults;


public class playground {


    private static void log(String msg, String... vals) {
        System.out.println(String.format(msg, vals));
    }


    public static void main(String[] args) {
        //Friday
        //31st December 2004
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dateBefore = LocalDate.of(2004, Month.DECEMBER, 31);
        //Friday
        //31st December 2010
        LocalDate dateAfter = LocalDate.of(2010, Month.DECEMBER, 31);
        //System.out.println(getFreidaysBetwn2004_und2010(dateBefore, dateAfter));
        //System.out.println("Size " + getFreidaysBetwn2004_und2010(dateBefore, dateAfter).size());


        List<String> daysofDraws = getFreidaysBetwn2004_und2010(dateBefore, dateAfter);//getDaysRangeByDaysOfWekk(LocalDate.parse("03-07-2018",
                //formatter), LocalDate.now(), DayOfWeek.TUESDAY, DayOfWeek.FRIDAY);



        System.out.println("------------------------------------");
        //System.out.println(daysofDraws);
        DAOmysql connect = new DAOmysql();
        try {
                doInsert(connect.getConnection(),getRemoteEuroResults(daysofDraws) );

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("------------------------------------");




        /*DAOmysql connect = new DAOmysql();
        try {
            List<Person> p = getAllUsers(connect.getConnection());
            System.out.println(p.toString());
            System.out.println(p.size());


            System.out.println("Last Elemet: "+getLastElement(connect.getConnection()));
        } catch (Exception e) {
            e.printStackTrace();
        }*/



       /* Document doc = null;
        try {
            doc = Jsoup.connect("https://www.euro-millions.com/results/06-07-2018").get();

            log(doc.title());
            Element drawsNor = doc.getElementById("jsBallOrderCell");
            Elements drawsDate = doc.getElementsByClass("draw-date");
            Elements drawsnumber = doc.getElementsByClass("draw-number");
            System.out.println("Draw Number is: "+ drawsnumber.text().split(":")[1].trim());

            System.out.println("------------------------------------");
            System.out.println("Draw Date is: "+ convertToLocalDate(drawsDate.text()));
            System.out.println("------------------------------------");

            System.out.println("Draw balls");
            for (Element st : drawsNor.getElementsByClass("ball") ) {
                System.out.println(st.text());
            }
            System.out.println();

            System.out.println("Lucky draws:");
            for (Element st : drawsNor.getElementsByClass("lucky-star") ) {
                System.out.println(st.text());
            }

            System.out.println(drawsDate.text());



        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }*/



       /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        long i = 1;
        while (dateBefore.isBefore(dateAfter)){
            if(dateBefore.getDayOfWeek() == DayOfWeek.FRIDAY){
                System.out.println("count: "+i +" "+dateBefore.format(formatter));
            }
            dateBefore = dateBefore.plusDays(7);
            i++;
        }


        System.out.println(dateAfter.format(formatter));*/


    }



    /*
    *  Document doc = null;
        try {
            doc = Jsoup.connect("https://www.euro-millions.com/results/06-07-2018").get();

            log(doc.title());
            Element drawsNor = doc.getElementById("jsBallOrderCell");
            Elements drawsDate = doc.getElementsByClass("draw-date");
            for (Element st : drawsNor.getElementsByClass("ball") ) {
                System.out.println(st.text());
            }
            System.out.println();

            for (Element st : drawsNor.getElementsByClass("lucky-star") ) {
                System.out.println(st.text());
            }

            System.out.println(drawsDate.text());



        } catch (IOException e) {
            e.printStackTrace();
        }*/


    //public static List<>

}
