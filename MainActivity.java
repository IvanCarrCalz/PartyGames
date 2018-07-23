package com.route.ivanc.partygames;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.route.ivanc.partygames.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User("Iveek");
        Challenge challenge = new Challenge("texto del reto 1", 12, 4);

        binding.setCurrentUser(user);
        binding.setCurrentChallenge(challenge);

    }
}
