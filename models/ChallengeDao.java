package com.route.ivanc.partygames.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ChallengeDao {

    @Query("SELECT * FROM challenges")
    List<Challenge> getAllChallenges();

    @Query("SELECT * FROM challenges WHERE challengeid = :challengeID")
    Challenge getChallengeById(String challengeID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertChallenge(Challenge challenge);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertChallenges(Challenge... challenges);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertChallenges(List<Challenge> challenges);

    @Delete
    void deleteChallenge(Challenge challenge);

    @Delete
    void deleteChallenges(Challenge... challenges);

    @Delete
    void deleteChallenges(List<Challenge> challenges);
}

