package com.euroMillio.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class sampleMain {

    public static void main(String[] args) {

        String dateInString = "Friday 6th December 2018";
        SimpleDateFormat formatter1 = new SimpleDateFormat("EEEE dd MMM yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String[] spt = dateInString.split(" ");
        spt[1] = spt[1].replaceAll("[^0-9]", "");


        try {

            Date date = formatter1.parse(String.join(" ", spt));

            System.out.println(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String datel = "Friday 3rd December 2018";
        ///System.out.println(Stream.of(datel.split(" ")).map(e->e.replaceAll("[0-9][a-zA-Z]+","")).collect(Collectors.joining(" ")));
    }
}
