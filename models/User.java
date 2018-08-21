package com.route.ivanc.partygames.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ivanc on 23/07/2018.
 */

public class User implements TextPointsShotsInterface {

    private String username;
    private int points;
    private int shots;

    public User(String username) {
        this.username = username;
        points = 0;
        shots = 0;
    }

    public User(String username, int shots) {
        this.username = username;
        points = 0;
        this.shots = shots;
    }

    public String getText() {
        return getUsername();
    }
    public String getUsername() {
        return username;
    }
    public int getPoints() {
        return points;
    }
    public int getShots() {
        return shots;
    }

    public void setText(String text){
        setUsername(text);
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public void setShots(int shots) {
        this.shots = shots;
    }

    public void addPoints(int points) {
        this.points += points;
    }
    public void addShots(int shots) {
        this.shots += shots;
    }

    public static void stuff() {
        System.out.println("MENSAJE");
    }
}
