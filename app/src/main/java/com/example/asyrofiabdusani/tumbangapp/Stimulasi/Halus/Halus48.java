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

public class Halus48 extends AppCompatActivity {

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
                getString(R.string.lanjutkan48h)));
        list.add(new StimulasiList("Konsep Tentang Separuh/Satu",
                "",getString(R.string.konsep_separuh_satu)));
        list.add(new StimulasiList("Menggambar","",
                getString(R.string.menggambar)));
        list.add(new StimulasiList("Mencocokkan dan Berhitung",
                "", getString(R.string.mencocokkan_dan_menghitung)));
        list.add(new StimulasiList("Menggunting","",
                getString(R.string.menggunting)));
        list.add(new StimulasiList("Membandingkan Besar/Kecil, Banyak/Sedikit, Berat/Ringan",
                "", getString(R.string.membandingkan_besar_kecil_banyak_sedikit_berat_ringan)));
        list.add(new StimulasiList("Percobaan Ilmiah","",
                getString(R.string.percobaan_ilmiah)));
        list.add(new StimulasiList("Berkebun",
                "", getString(R.string.berkebun)));
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