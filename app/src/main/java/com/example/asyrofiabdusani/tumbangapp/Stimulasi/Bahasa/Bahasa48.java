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

public class Bahasa48 extends AppCompatActivity {

    private StimulasiListAdapter mAdapter;
    private ArrayList<StimulasiList> list = new ArrayList<StimulasiList>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerak_halus);

        listItem();
        listView = (ListView) findViewById(R.id.list_stimulasi);
        mAdapter= new StimulasiListAdapter(this,list);
        listView.setAdapter(mAdapter);
    }
    private void listItem() {
        list.add(new StimulasiList("","Stimulasi Yang Perlu Dilanjutkan",
                getString(R.string.lanjutkan48bb)));
        list.add(new StimulasiList("Belajar Mengingat - Ingat","",
                getString(R.string.belajar_mengingat)));
        list.add(new StimulasiList("Mengenal Huruf dan Simbol","",
                getString(R.string.mengenal_huruf_dan_simbol)));
        list.add(new StimulasiList("Mengenal Angka","",
                getString(R.string.mengenal_angka)));
        list.add(new StimulasiList("Mengenal Musin","",
                getString(R.string.mengenal_musim)));
        list.add(new StimulasiList("Buku Kegiatan Keluarga","",
                getString(R.string.buku_kegiatan_keluarga)));
        list.add(new StimulasiList("Mengunjungi Perpustakaan","",
                getString(R.string.mengunjungi_perpustakaan)));
        list.add(new StimulasiList("Melengkapi Kalimat","",
                getString(R.string.melengkapi_kalimat)));
        list.add(new StimulasiList("Bercerita Ketika Saya Masih Kecil","",
                getString(R.string.bercerita_ketika_saya_masih_kecil)));
        list.add(new StimulasiList("Membantu Pekerjaan Di Dapur","",
                getString(R.string.membantu_pekerjaan_didapur)));
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

