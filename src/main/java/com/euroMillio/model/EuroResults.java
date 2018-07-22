package com.euroMillio.model;

import java.util.*;

public class EuroResults {

    String data;
    private ArrayList<String> balls_drawn = new ArrayList<>();
    private ArrayList<String> lucky_stars = new ArrayList<>();
    long drawNr;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getBalls_drawn() {
        return Collections.unmodifiableList(balls_drawn);
    }

    public void setBalls_drawn(Collection<String> balls_drawn) {
        this.balls_drawn = new ArrayList<>(balls_drawn)  ;
    }

    public List<String> getLucky_stars() {
        return Collections.unmodifiableList(lucky_stars);
    }

    public void setLucky_stars(Collection<String> lucky_stars) {
        this.lucky_stars = new ArrayList<>(lucky_stars);
    }

    public long getDrawNr() {
        return drawNr;
    }



    public void setDrawNr(long drawNr) {
        this.drawNr = drawNr;
    }
    @Override
    public String toString() {
        return  data + ' ' + balls_drawn +' '+ lucky_stars + ' ' + drawNr ;
    }
}
