package com.example.asyrofiabdusani.tumbangapp.Stimulasi.Halus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class Halus36 extends AppCompatActivity {

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
        list.add(new StimulasiList("",
                "Stimulasi Yang Perlu Dilanjutkan",getString(R.string.lanjutkan36h)));
        list.add(new StimulasiList("Memotong",
                "",getString(R.string.memotong)));
        list.add(new StimulasiList("Membuat Buku Cerita Gambar Tempel",
                "",getString(R.string.membuat_buku_cerita_gambar_tempel)));
        list.add(new StimulasiList("Menempel Gambar","",
                getString(R.string.menempel_gambar)));
        list.add(new StimulasiList("Menjahit",
                "",getString(R.string.menjahit)));
        list.add(new StimulasiList("Membuat Gambar Tempel","",
                getString(R.string.membuat_gambar_tempel)));
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