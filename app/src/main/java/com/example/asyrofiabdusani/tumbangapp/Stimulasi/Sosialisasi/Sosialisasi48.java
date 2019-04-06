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

public class Sosialisasi48 extends AppCompatActivity {
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
                getString(R.string.lanjutkan48s)));
        list.add(new StimulasiList("Membentuk Kemandirian","",getString(R.string.membentuk_kemandirian)));
        list.add(new StimulasiList("Membuat Album Keluarga","",getString(R.string.membuat_album_keluarga)));
        list.add(new StimulasiList("Membuat Boneka","", getString(R.string.membuat_boneka)));
        list.add(new StimulasiList("Menggambar Orang","",
                getString(R.string.menggambar_orang)));
        list.add(new StimulasiList("Mengikuti Aturan/Petunjuk Permainan","",
                getString(R.string.mengikuti_aturan_permainan)));
        list.add(new StimulasiList("Bermain Kreatid Dengan Teman - Temannya","",
                getString(R.string.bermain_kreatif_dengan_temannya)));
        list.add(new StimulasiList("Bermain Berjualan dan Berbelanja Di Toko","",
                getString(R.string.bermain_berjualan_dan_berbelanja_di_toko)));

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