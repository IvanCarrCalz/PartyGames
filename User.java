package com.route.ivanc.partygames;

import android.databinding.BindingConversion;

/**
 * Created by ivanc on 23/07/2018.
 */

public class User {
    public String username;
    public int points;
    public int shots;

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

    public String getUsername() {
        return username;
    }
    public int getPoints() {
        return points;
    }
    public int getShots() {
        return shots;
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
}
