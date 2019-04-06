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

public class Halus24 extends AppCompatActivity {

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
                getString(R.string.lanjutkan24h)));
        list.add(new StimulasiList("Membuat Gambar Tempelan","",
                getString(R.string.membuat_gambar_tempelan)));
        list.add(new StimulasiList("Memilih dan Mengelompokkan Benda - Benda Menurut Jenisnya",
                "",getString(R.string.memilih_dan_mengelompokkan_benda_berdasar_jenisnya)));
        list.add(new StimulasiList("Mencocokkan Gambar dan Benda","",
                getString(R.string.mencocokkan_gambar_dan_benda)));
        list.add(new StimulasiList("Konsep Jumlah","",
                getString(R.string.konsep_jumlah)));
        list.add(new StimulasiList("Bermain/Menyusun Balok - Balokan","",
                getString(R.string.bermain_atau_menyusun_balok)));
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