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

public class Bahasa24 extends AppCompatActivity {
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
                getString(R.string.lanjutkan24bb)));
        list.add(new StimulasiList("Menyebut Nama Lengkap Anak","",
                getString(R.string.menyebut_nama_lengkap_anak)));
        list.add(new StimulasiList("Bercerita Tentang Diri Anak","",
                getString(R.string.bercerita_tentang_diri_anak)));
        list.add(new StimulasiList("Menyebut Nama Berbagai Jenis Pakaian","",
                getString(R.string.menyebut_nama_berbagai_jenis_pakaian)));
        list.add(new StimulasiList("Menyatakan Keadaan Suatu Benda","",
                getString(R.string.menyatakan_keadaan_sautu_benda)));
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
