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

public class Kasar36 extends AppCompatActivity {

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
                getString(R.string.lanjutkan36k)));
        list.add(new StimulasiList("Menangkap Bola","",getString(R.string.menangkap_bola)));
        list.add(new StimulasiList("Berjalan Mengikuti Garis Lurus","",
                getString(R.string.berjalan_mengikuti_garis_lurus)));
        list.add(new StimulasiList("Melompat","", getString(R.string.melompat)));
        list.add(new StimulasiList("Melempar Benda - Benda Kecil Ke Atas","",
                getString(R.string.melempar_benda_kecil_keatas)));
        list.add(new StimulasiList("Menirukan Binatang Berjalan","",
                getString(R.string.menirukan_binatang_berjalan)));
        list.add(new StimulasiList("Lampu Hijau - Merah","", getString(R.string.lempu_hijau_merah)));
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
