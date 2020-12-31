package com.test.rh.runkeeper;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<RaceModel> raceList = new ArrayList<>();

        raceList.add(new RaceModel("Personal Records", "5 of 6", "", 0, 0, 0));
        raceList.add(new RaceModel("Longest Run", "00:00:00", "capture1", 2020, 1, 0));
        raceList.add(new RaceModel("Highest Elevation", "00:00:00", "capture2", 2020, 1, 1197));
        raceList.add(new RaceModel("Fastest 5K", "00:00:00", "capture3", 2019, 1, 0));
        raceList.add(new RaceModel("Fastest 10K", "00:00:00", "capture4", 2019, 1, 0));
        raceList.add(new RaceModel("Fastest Half Marathon", "00:00:00", "capture5", 2019, 1, 0));
        raceList.add(new RaceModel("Fastest Full Merathon", "00:00:00", "capture6", 2019, 1, 0));
        raceList.add(new RaceModel("Virtual Races", " ", "", 0, 0, 0));
        raceList.add(new RaceModel("Virtual Half Marathon Race", "00:00:00", "capture7", 2021, 1, 0));
        raceList.add(new RaceModel("Tokyo-Hakone Ekiden 2020", "00:00:00", "capture8", 2020, 1, 0));
        raceList.add(new RaceModel("Virtual 10K Race", "00:00:00", "capture9", 2020, 1, 0));
        raceList.add(new RaceModel("Hakone Ekiden", "00:00:00", "capture10", 2020, 1, 0));
        raceList.add(new RaceModel("Mizuno Singapore Ekiden 2015", "00:00:00", "capture11", 2020, 1, 0));
        raceList.add(new RaceModel("Virtual 5K Race", "00:00:00", "capture12", 2020, 1, 0));

        RecAdapter adapter = new RecAdapter(raceList, getPackageName());

        RecyclerView recyclerView = findViewById(R.id.recview);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        //  recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int span = 1;
                if (raceList.get(position).getViewType() == 0)
                    span = 2;
                return span;
            }
        });
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}