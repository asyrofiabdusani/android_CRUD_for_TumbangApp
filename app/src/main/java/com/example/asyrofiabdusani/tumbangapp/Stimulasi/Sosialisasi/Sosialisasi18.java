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

public class Sosialisasi18 extends AppCompatActivity {

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
                getString(R.string.lanjutkan18s)));
        list.add(new StimulasiList("Mengancingkan Kancing Baju","",
                getString(R.string.mengancingkan_kancing_baju)));
        list.add(new StimulasiList("Permainan Yang Memerlukan Interaksi Dengan Teman Bermain",
                "",getString(R.string.permanan_yang_memerlukan_interaksi_dengan_teman)));
        list.add(new StimulasiList("Membuat Rumah - Rumahan","", getString(R.string.membuat_rumah_rumahan)));
        list.add(new StimulasiList("Berpakaian",
                "",getString(R.string.berpakaian)));
        list.add(new StimulasiList("Memisahkan Diri Dengan Anak","",
                getString(R.string.memisahkan_diri_dengan_anak)));

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