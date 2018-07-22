package com.euroMillio.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class commons {


    public static List<String> getFreidaysBetwn2004_und2010(
            LocalDate startDate, LocalDate endDate) {

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("Generating all sample draw days dates.....");

        return IntStream.iterate(0, i -> i + 7)
                .limit(numOfDaysBetween / 7 + 1)
                .mapToObj(i -> startDate.plusDays(i)).filter(e -> e.getDayOfWeek().equals(DayOfWeek.FRIDAY))
                .map(y -> y.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .collect(Collectors.toList());
    }

    public static List<String> getDaysRangeByDaysOfWekk(
            LocalDate startDate, LocalDate endDate, DayOfWeek dayOfWeek1, DayOfWeek dayOfWeek2) {

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        return IntStream.iterate(1, i -> i + 1)
                .limit(numOfDaysBetween)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i)).filter(e -> e.getDayOfWeek().equals(dayOfWeek1) || e.getDayOfWeek().equals(dayOfWeek2))
                .map(y -> y.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .collect(Collectors.toList());
    }

    public static String convertToLocalDate(String dateInString) throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("EEEE dd MMM yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String[] spt = dateInString.split(" ");
        spt[1] = spt[1].replaceAll("[^0-9]", "");


        try {

            Date date = formatter1.parse(String.join(" ", spt));

           return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter);

        } catch (ParseException e) {
             throw e;
        }
    }

    public static List<EuroResults> getRemoteEuroResults(List<String> drawDays) throws Exception{

        List<EuroResults> euroResults = new ArrayList<>();


        Document doc = null;
        try {
            for (String day : drawDays) {
                EuroResults elm = new EuroResults();
                String url = "https://www.euro-millions.com/results/"+day;
                //get DOM data
                doc = Jsoup.connect(url).get();

                //extract the draw list
                Element drawsballs = doc.getElementById("jsBallOrderCell");

                //get the draw balls
                elm.setBalls_drawn(drawsballs.getElementsByClass("ball").stream().map(
                        Element::text).collect(Collectors.toList()));
                //get the draw date
                Elements drawsDate = doc.getElementsByClass("draw-date");
                elm.setData(convertToLocalDate(drawsDate.text()));
                //get the 3 licky stars
                elm.setLucky_stars(drawsballs.getElementsByClass("lucky-star").stream().map(
                        Element::text
                ).collect(Collectors.toList()));

                //get draw number
                Elements drawsnumber = doc.getElementsByClass("draw-number");
                elm.setDrawNr(Integer.parseInt(drawsnumber.text().split(":")[1].trim()));

                euroResults.add(elm);
            }

            System.out.println("All EuroMillion Results successfully extracted");
            return euroResults;
        } catch (IOException | ParseException e) {
            throw e;
        }

    }
}
