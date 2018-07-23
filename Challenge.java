package com.route.ivanc.partygames;

/**
 * Created by ivanc on 23/07/2018.
 */

public class Challenge {
    public String text;
    public int points;
    public int shots;

    public Challenge(String text, int points, int shots) {
        this.text = text;
        this.points = points;
        this.shots = shots;
    }

    public String getText() {
        return text;
    }
    public int getPoints() {
        return points;
    }
    public int getShots() {
        return shots;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public void setShots(int shots) {
        this.shots = shots;
    }

}
