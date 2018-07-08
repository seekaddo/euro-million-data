package com.euroMillio.model;

public class EuroResults {

    String data;
    String balls_drawn;
    String lucky_stars;
    long drawNr;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBalls_drawn() {
        return balls_drawn;
    }

    public void setBalls_drawn(String balls_drawn) {
        this.balls_drawn = balls_drawn;
    }

    public String getLucky_stars() {
        return lucky_stars;
    }

    public void setLucky_stars(String lucky_stars) {
        this.lucky_stars = lucky_stars;
    }

    public long getDrawNr() {
        return drawNr;
    }



    public void setDrawNr(long drawNr) {
        this.drawNr = drawNr;
    }
    @Override
    public String toString() {
        return  '['+data +']'+ ' ' + balls_drawn +' '+ '['+ lucky_stars+']' + ' ' + drawNr ;
    }
}
