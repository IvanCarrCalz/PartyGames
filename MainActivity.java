package com.route.ivanc.partygames;

import android.annotation.TargetApi;
import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.route.ivanc.partygames.models.Challenge;
import com.route.ivanc.partygames.models.MyDatabase;
import com.route.ivanc.partygames.models.TextPointsShotsInterface;
import com.route.ivanc.partygames.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MyDatabase myDatabase;

    Data challengeData;
    ArrayList<User> users;
    ArrayList<UserField> userFields, userFieldsOrdered;
    UserField currentUserField;
    ArrayList<Challenge> challenges;
    Challenge currentChallenge;

    Button completed, failed;

    private class Data {
        TextView text, points, shots;

        Data(View text, View points, View shots) {
            this.text = (TextView) text;
            this.points = (TextView) points;
            this.shots = (TextView) shots;
        }

    }
    private class UserField {
        LinearLayout linearLayout;
        Data data;
        User user;

        UserField (AppCompatActivity context, User user) {
            this.linearLayout = new LinearLayout(context);
            this.linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            this.linearLayout.setOrientation(LinearLayout.VERTICAL);

            TextView textView_text = new TextView(context);
                textView_text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            TextView textView_points = new TextView(context);
                textView_points.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            TextView textView_shots = new TextView(context);
                textView_shots.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            this.linearLayout.addView(textView_text);
            this.linearLayout.addView(textView_points);
            this.linearLayout.addView(textView_shots);

            this.data = new Data(
                    textView_text,
                    textView_points,
                    textView_shots
            );
            this.user = user;

            setData(this.data, this.user);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        void show(boolean selected) {
            setData(this.data, this.user);
            this.linearLayout.setVisibility(View.VISIBLE);
            if (selected) {
                this.linearLayout.setBackgroundColor(Color.argb(255,255,0,0));
            }
            else
                this.linearLayout.setBackgroundColor(Color.argb(255,255,255,255));
        }

    }
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDatabase = Room.databaseBuilder(this, MyDatabase.class, MyDatabase.NAME).fallbackToDestructiveMigration().build();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myDatabase.challengeDao().deleteChallenges(myDatabase.challengeDao().getAllChallenges());
                if (myDatabase.challengeDao().getAllChallenges().size() < 5) {
                    Challenge c1 = new Challenge(1,"Haz el pino durante 10 segundos", 5, 2);
                    Challenge c2 = new Challenge(2,"Corre contra una pared e intenta atravesarla", 10, 2);
                    Challenge c3 = new Challenge(3,"Hazle un Kame Hame Ha al camarero", 5, 4);
                    Challenge c4 = new Challenge(4,"Nombre 10 pokémon distintos en 15 segundos", 5, 4);
                    Challenge c5 = new Challenge(5,"Quítate 2 prendas", 10, 4);
                    List<Challenge> l = new ArrayList<>();
                    l.add(c1);
                    l.add(c2);
                    l.add(c3);
                    l.add(c4);
                    l.add(c5);
                    myDatabase.challengeDao().insertChallenges(l);
//                    myDatabase.challengeDao().insertChallenges(c1, c2, c3, c4, c5);
                }
            }
        });
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        challengeData = new Data(
                findViewById(R.id.challengeText),
                findViewById(R.id.pointsVictory),
                findViewById(R.id.shotsDefeat)
        );


        users = new ArrayList<>();
        users.add(new User("Iveek"));
        users.add(new User("Sobaco"));
        users.add(new User("Otro"));
        for (int i = 0; i < 5; i++)
            users.add(new User(Challenge.generateRandomString(10)));

        int userIndex = (new Random()).nextInt(users.size());

        LinearLayout horizontalScrollView = findViewById(R.id.users);
        userFields = new ArrayList<>();
        for (User u: users) {
            UserField uf = new UserField(this, u);
            userFields.add(uf);
            horizontalScrollView.addView(uf.linearLayout);
        }

        for (UserField uf: userFields) {
            uf.show(false);
        }

        userFieldsOrdered = (ArrayList<UserField>)userFields.clone();

        currentUserField = userFields.get(userIndex);
        currentUserField.show(true);

//        challenges = new ArrayList<>();
//        for (int i = 0; i < 25; i++)
//            challenges.add(Challenge.randomChallenge());

        challenges = new ArrayList<>();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                challenges.addAll(myDatabase.challengeDao().getAllChallenges());

            }
        });
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int challengeIndex = (new Random()).nextInt(challenges.size());
        currentChallenge = challenges.get(challengeIndex);

        completed = findViewById(R.id.completedBtn);
        failed = findViewById(R.id.shotsBtn);

        completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action(true);

            }
        });
        failed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action(false);
            }
        });

        setData(challengeData, currentChallenge);
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void action(boolean win) {
        if (win)
            currentUserField.user.addPoints(currentChallenge.getPoints());
        else
            currentUserField.user.addShots(currentChallenge.getShots());
        currentUserField.show(false);

        currentUserField = userFields.get((userFields.indexOf(currentUserField) + 1) % users.size());
        currentUserField.show(true);
        challenges.remove(currentChallenge);

        try {
            currentChallenge = challenges.get((new Random()).nextInt(challenges.size()));
            setData(challengeData, currentChallenge);
        } catch (Exception e) {
            setData(challengeData, new Challenge("No hay más retos", 0, 0));
            completed.setEnabled(false);
            failed.setEnabled(false);
        }

    }

    private void setData(Data fields, TextPointsShotsInterface data) {
        String text = data.getText();
        String points = String.valueOf(data.getPoints());
        String shots = String.valueOf(data.getShots());
        fields.text.setText(text);
        fields.points.setText("Puntos: " + points);
        fields.shots.setText("Chupitos:" + shots);
    }


}
