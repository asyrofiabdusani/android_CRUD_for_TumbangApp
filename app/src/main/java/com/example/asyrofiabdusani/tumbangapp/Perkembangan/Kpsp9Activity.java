package com.example.asyrofiabdusani.tumbangapp.Perkembangan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class Kpsp9Activity extends AppCompatActivity {
    private String currentId;
    private String kategori1 [] = new String[9];
    private String kategori2 [] = new String[10];
    private int usia;
    private int ceklis1[] = new int[9];
    private int ceklis2[] = new int[10];
    private KpspListAdapter mAdapter;
    private ArrayList<KpspList> list = new ArrayList<KpspList>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kpsp_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getVal();
        listItem();
        listView = (ListView) findViewById(R.id.list_stimulasi);
        mAdapter= new KpspListAdapter(this,list);
        listView.setAdapter(mAdapter);

        Button nextButton = (Button)findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCheckBox();
                if ((usia >= 42 && usia <= 53)) {
                    ceklis2 [9] = 0;
                    kategori2 [9] = "tidak ada";
                    Intent nextIntent = new Intent(Kpsp9Activity.this, PrepareActivity.class);
                    nextIntent.putExtra("id", currentId);
                    nextIntent.putExtra("umur", usia);
                    nextIntent.putExtra("ceklis", ceklis2);
                    nextIntent.putExtra("kategori", kategori2);
                    startActivity(nextIntent);
                }else {
                    Intent nextIntent = new Intent(Kpsp9Activity.this, Kpsp10Activity.class);
                    nextIntent.putExtra("id", currentId);
                    nextIntent.putExtra("umur", usia);
                    nextIntent.putExtra("ceklis", ceklis2);
                    nextIntent.putExtra("kategori", kategori2);
                    startActivity(nextIntent);
                }
            }
        });
    }

    private void getVal(){
        Bundle extra = getIntent().getExtras();
        currentId = extra.getString("id");
        usia = extra.getInt("umur");
        ceklis1 = extra.getIntArray("ceklis");
        kategori1 = extra.getStringArray("kategori");
    }

    private void getCheckBox () {
        KpspList listPertanyaan = (KpspList) mAdapter.getItem(0);

        for (int i = 0; i<8; i++){
            ceklis2 [i] = ceklis1 [i];
            kategori2 [i] = kategori1 [i];
        }
        ceklis2 [8] = listPertanyaan.getmJawaban();;
        kategori2 [8] = listPertanyaan.getmKategori();
    }

    private void listItem() {
        if (usia>=11 && usia<=14){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.sebut2Kata),"Bahasa dan Bicara"));
        }
        if (usia>=15 && usia<=17){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.berjalanSepanjangRuangan),"Gerak Kasar"));
        }
        if (usia>=18 && usia<=20){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.menggelindingkanBola),"Gerak Halus, Sosialisasi dan Kemandirian"));
        }
        if (usia>=21 && usia<=23){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.mengucapkan3kata),"Bahasa dan Bicara"));
        }
        if (usia>=24 && usia<=29){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.memungutMainanSendiri),"Bahasa dan Bicara"));
        }
        if (usia>=30 && usia<=35){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.menggunakan2kata),"Bahasa dan Bicara"));
        }
        if (usia>=36 && usia<=41){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.mengenakanSepatu),"Sosialisasi dan Kemandirian"));
        }
        if (usia>=42 && usia<=47){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.mengenakanCelana),"Sosialisasi dan Kemandirian"));
        }
        if (usia>=48 && usia<=53){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.namaLengkap),"Bahasa dan Bicara"));
        }
        if (usia>=54 && usia<=59){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.gambarPlus), R.drawable.gbrplus, "Gerak Halus"));
        }
        if (usia>=60 && usia<=65){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.melompatSatuKaki),"Gerak Kasar"));
        }
        if (usia>=66 && usia<=71){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.tulisYangDikatakanAnak),"Bahasa dan Bicara"));
        }
        if (usia==72){
            list.add(new KpspList("Pertanyaan 9", getString(R.string.gambarKotak),"Gerak Halus"));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

