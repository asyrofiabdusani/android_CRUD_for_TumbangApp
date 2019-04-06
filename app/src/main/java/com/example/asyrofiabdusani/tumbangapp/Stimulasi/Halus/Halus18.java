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

public class Halus18 extends AppCompatActivity {

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
        list.add(new StimulasiList("","Stimulasi Yang perlu Dilanjutkan",
                getString(R.string.lanjutkan18h)));
        list.add(new StimulasiList("Mengenal Berbagai Ukuran dan Bentuk","",
                getString(R.string.mengenal_berbagai_ukuran_dan_bentuk)));
        list.add(new StimulasiList("Bermain Puzzle","",
                getString(R.string.bermain_puzzle)));
        list.add(new StimulasiList("Menggambar Wajah atau Bentuk","",
                getString(R.string.menggambar_wajah_atau_bentuk)));
        list.add(new StimulasiList("Membuat Berbagai Bentuk Dari Adonan/Lilin Mainan","",
                getString(R.string.membuat_berbagai_bentuk_dari_adonan_kue_atau_lilin_mainan)));
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
