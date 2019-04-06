package com.example.asyrofiabdusani.tumbangapp.Stimulasi.Sosialisasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class Sosialisasi15 extends AppCompatActivity {

    private StimulasiListAdapter mAdapter;
    private ArrayList<StimulasiList> list = new ArrayList<StimulasiList>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerak_halus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listItem();
        listView = (ListView) findViewById(R.id.list_stimulasi);
        mAdapter= new StimulasiListAdapter(this,list);
        listView.setAdapter(mAdapter);
    }
    private void listItem() {
        list.add(new StimulasiList("","Stimulasi Yang Perlu Dilanjutkan",
                getString(R.string.lanjutkan15s)));
        list.add(new StimulasiList("Memeluk dan Mencium","",getString(R.string.memeluk_dan_mencium)));
        list.add(new StimulasiList("Membereskan Mainan/Membantu Kegiatan Di Rumah","",
                getString(R.string.membereskan_mainan_atau_membereskan_pekerjaan_rumah)));
        list.add(new StimulasiList("Bermain Dengan Teman Sebaya","", getString(R.string.bermain_dengan_teman_sebaya)));
        list.add(new StimulasiList("Permainan Baru","",
                getString(R.string.permainan_baru)));
        list.add(new StimulasiList("Bermain Petak Umpet","", getString(R.string.bermain_petak_umpet)));

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