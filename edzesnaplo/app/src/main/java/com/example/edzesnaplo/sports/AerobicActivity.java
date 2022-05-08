package com.example.edzesnaplo.sports;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.edzesnaplo.ProfileSettingsActivity;
import com.example.edzesnaplo.R;
import com.example.edzesnaplo.SportItem;
import com.example.edzesnaplo.WorkoutsListActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AerobicActivity extends AppCompatActivity {
    private static final String LOG_TAG = AerobicActivity.class.getName();
    private SportItem sportItem;
    private EditText timeEditText;
    private EditText kcalEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aerobic);
        findData();
    }

    private void findData() {
        timeEditText = findViewById(R.id.workoutTime);
        kcalEditText = findViewById(R.id.workoutKcal);
    }

    public void add(View view) {
        String name = "Aerobic";
        int time = Integer.parseInt(timeEditText.getText().toString());
        int kcal = Integer.parseInt(kcalEditText.getText().toString());

        sportItem = new SportItem(name, time, kcal, 0);
        SportItem.WORKOUTS.add(sportItem);
        for (int i = 0; i < SportItem.WORKOUTS.size(); i++) {
            Log.d(LOG_TAG, SportItem.WORKOUTS.get(i).getName());
        }
        finish();
    }

    public void cancel(View view) {
        finish();
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
}
