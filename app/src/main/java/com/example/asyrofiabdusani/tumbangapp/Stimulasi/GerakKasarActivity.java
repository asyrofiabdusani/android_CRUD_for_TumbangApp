package com.example.asyrofiabdusani.tumbangapp.Stimulasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class GerakKasarActivity extends AppCompatActivity {
    private StimulusAdapter mAdapter;
    private ArrayList<ListStimulus> list = new ArrayList<ListStimulus>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerak_halus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listItem();
        listView = (ListView) findViewById(R.id.list_stimulasi);
        mAdapter= new StimulusAdapter(this,list);
        listView.setAdapter(mAdapter);
    }
    private void listItem() {
        list.add(new ListStimulus("9 - 12","Stimulasi Umur 9 - 12 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Kasar.Kasar9"));
        list.add(new ListStimulus("12 - 15","Stimulasi Umur 12 - 15 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Kasar.Kasar12"));
        list.add(new ListStimulus("15 - 18","Stimulasi Umur 15 - 18 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Kasar.Kasar15"));
        list.add(new ListStimulus("18 - 24","Stimulasi Umur 18 - 24 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Kasar.Kasar18"));
        list.add(new ListStimulus("24 - 36","Stimulasi Umur 24 - 36 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Kasar.Kasar24"));
        list.add(new ListStimulus("36 - 48","Stimulasi Umur 36 - 48 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Kasar.Kasar36"));
        list.add(new ListStimulus("48 - 60","Stimulasi Umur 48 - 60 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Kasar.Kasar48"));
        list.add(new ListStimulus("60 - 72","Stimulasi Umur 60 - 72 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Kasar.Kasar60"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        } else if(item.getItemId()==R.id.home){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}