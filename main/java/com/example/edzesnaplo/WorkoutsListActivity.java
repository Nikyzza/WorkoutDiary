package com.example.edzesnaplo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class WorkoutsListActivity extends AppCompatActivity {
    private static final String LOG_TAG = WorkoutsListActivity.class.getName();
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        listView = findViewById(R.id.listViewWorkouts);
        final ArrayAdapter<SportItem> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, SportItem.WORKOUTS);
        listView.setAdapter(adapter);
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
