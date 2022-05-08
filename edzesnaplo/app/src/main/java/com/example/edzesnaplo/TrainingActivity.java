package com.example.edzesnaplo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.edzesnaplo.sports.AerobicActivity;
import com.example.edzesnaplo.sports.ClimbingActivity;
import com.example.edzesnaplo.sports.CyclingActivity;
import com.example.edzesnaplo.sports.FootballActivity;
import com.example.edzesnaplo.sports.KayakingActivity;
import com.example.edzesnaplo.sports.LiftingActivity;
import com.example.edzesnaplo.sports.RunActivity;
import com.example.edzesnaplo.sports.SwimmingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TrainingActivity extends AppCompatActivity {
    private final static String LOG_TAG = TrainingActivity.class.getName();
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            Log.d(LOG_TAG, "Authenticated user!");
        } else {
            Log.d(LOG_TAG, "Unauthenticated user!");
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Log.d(LOG_TAG, "Logout clicked!");
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
            case R.id.profileSettings:
                Log.d(LOG_TAG, "Setting clicked!");
                Intent intent = new Intent(this, ProfileSettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.workouts:
                Log.d(LOG_TAG, "Workouts clicked!");
                Intent intentWorkout = new Intent(this, WorkoutsListActivity.class);
                startActivity(intentWorkout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startAerobic(View view) {
        Intent intent = new Intent(this, AerobicActivity.class);
        startActivity(intent);
    }

    public void startCycling(View view) {
        Intent intent = new Intent(this, CyclingActivity.class);
        startActivity(intent);
    }

    public void startFootball(View view) {
        Intent intent = new Intent(this, FootballActivity.class);
        startActivity(intent);
    }

    public void startRun(View view) {
        Intent intent = new Intent(this, RunActivity.class);
        startActivity(intent);
    }

    public void startClimbing(View view) {
        Intent intent = new Intent(this, ClimbingActivity.class);
        startActivity(intent);
    }

    public void startKayaking(View view) {
        Intent intent = new Intent(this, KayakingActivity.class);
        startActivity(intent);
    }

    public void startLifting(View view) {
        Intent intent = new Intent(this, LiftingActivity.class);
        startActivity(intent);
    }

    public void startSwimming(View view) {
        Intent intent = new Intent(this, SwimmingActivity.class);
        startActivity(intent);
    }

}
