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

public class Halus60 extends AppCompatActivity {

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
                getString(R.string.lanjutkan60h)));
        list.add(new StimulasiList("Mengerti Urutan Kegiatan","",
                getString(R.string.mengerti_urutan_kegiatan)));
        list.add(new StimulasiList("Berlatih Mengingat - Ingat","",
                getString(R.string.berlatih_mengingat_ingat)));
        list.add(new StimulasiList("Membuat Sesuatu Dari Tanah Liat/Lilin",
                "", getString(R.string.membuat_sesuatu_dari_tanah_liat_dan_lilin)));
        list.add(new StimulasiList("Bermain Berjualan","",
                getString(R.string.bermain_berjualan)));
        list.add(new StimulasiList("Belajar Bertukang Memakai Palu, Gergaji dan Paku",
                "", getString(R.string.belajar_bertukang_memakai_pali_gergaji_dan_paku)));
        list.add(new StimulasiList("Mengumpulkan Benda - Benda","",
                getString(R.string.mengumpulkan_benda_benda)));
        list.add(new StimulasiList("Belajar Memasak","",
                getString(R.string.belajar_memasak)));
        list.add(new StimulasiList("Mengenal Kalender",
                "", getString(R.string.mengenal_kalender)));
        list.add(new StimulasiList("Mengenal Waktu","",
                getString(R.string.mengenal_waktu)));
        list.add(new StimulasiList("Menggambar Dari Berbagai Sudut Pandang",
                "", getString(R.string.menggambar_dari_berbagai_sudut_pandang)));
        list.add(new StimulasiList("Belajar Mengukur","",
                getString(R.string.belajar_mengukur)));
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
