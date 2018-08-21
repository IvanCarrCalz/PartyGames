package com.route.ivanc.partygames.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Random;
import java.util.UUID;

/**
 * Created by ivanc on 23/07/2018.
 */

@Entity(tableName = "challenges")
public class Challenge implements TextPointsShotsInterface {

    @PrimaryKey
    @ColumnInfo(name = "challengeid")
    private long id;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "points")
    private int points;

    @ColumnInfo(name = "shots")
    private int shots;


    @Ignore
    public Challenge(String text, int points, int shots) {
        id = System.currentTimeMillis();
        this.text = text;
        this.points = points;
        this.shots = shots;
    }

    @Ignore
    public Challenge(int id, String text, int points, int shots) {
        this.id = (long) id;
        this.text = text;
        this.points = points;
        this.shots = shots;
    }

    public Challenge(long id, String text, int points, int shots) {
        this.id = id;
        this.text = text;
        this.points = points;
        this.shots = shots;
    }

    public long getId() {
        return id;
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

    public void setId(long id) {
        this.id = id;
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


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String generateRandomString(int length) {
        Random random = new Random();
        return random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(random.nextInt(length))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Challenge randomChallenge() {
        Random random = new Random();
        return new Challenge(
                generateRandomString(64),
                random.nextInt(30),
                random.nextInt(10));
    }
}
