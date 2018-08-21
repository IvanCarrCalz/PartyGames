package com.route.ivanc.partygames.models;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities={Challenge.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract ChallengeDao challengeDao();

    public static final String NAME = "MyDataBase";
}
