package com.example.asyrofiabdusani.tumbangapp.Stimulasi.Bahasa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class Bahasa60 extends AppCompatActivity {
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
                getString(R.string.lanjutkan60bb)));
        list.add(new StimulasiList("Mengenal Benda Yang Serupa dan Berbeda","",
                getString(R.string.mengenal_benda_yang_serupa_dan_berbeda)));
        list.add(new StimulasiList("Bermain Tebak - Tebakan","",
                getString(R.string.bermain_tebak_tebakan)));
        list.add(new StimulasiList("Berlatih Mengingat - Ingat","",
                getString(R.string.berlatih_mengingat_ingat)));
        list.add(new StimulasiList("Menjawab Pertanyaan Mengapa","",
                getString(R.string.menjawab_pertanyaan_mengapa)));
        list.add(new StimulasiList("Mengenal Rambu/Tanda Lalu Lintas","",
                getString(R.string.mengenal_rambu_tanda_lalu_lintas)));
        list.add(new StimulasiList("Mengenal Uang Logam","",
                getString(R.string.mengenal_uang_logam)));
        list.add(new StimulasiList("Mengamati/Meneliti Keadaan Sekitarnya","",
                getString(R.string.mengamati_atau_meneliti_hal_disekitar)));
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
