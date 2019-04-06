package com.example.asyrofiabdusani.tumbangapp.Stimulasi.Kasar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class Kasar12 extends AppCompatActivity {

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
                getString(R.string.lanjutkan12k)));
        list.add(new StimulasiList("Menarik Mainan","",
                getString(R.string.menarik_mainan)));
        list.add(new StimulasiList("Berjalan Mundur","",
                getString(R.string.berjalan_mundur)));
        list.add(new StimulasiList("Berjalan Naik dan Turun Tangga",
                "", getString(R.string.berjalan_naik_dan_turun_tangga)));
        list.add(new StimulasiList("Berjalan Sambil Berjinjit","",
                getString(R.string.berjalan_sambil_berjinjit)));
        list.add(new StimulasiList("Menangkap dan Melempar Bola","",
                getString(R.string.menangkap_dan_melempar_bola)));
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
