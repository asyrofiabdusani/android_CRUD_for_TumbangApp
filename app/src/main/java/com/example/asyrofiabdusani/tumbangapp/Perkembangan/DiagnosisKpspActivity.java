package com.example.asyrofiabdusani.tumbangapp.Perkembangan;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asyrofiabdusani.tumbangapp.Data.DetailDataActivity;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract.perkembangan;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;
import com.example.asyrofiabdusani.tumbangapp.Stimulasi.StimulasiActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DiagnosisKpspActivity extends AppCompatActivity {
    private Cursor cursor;
    private TumbangDbHelper mDbHelper;
    private TextView nama, ibu, lahir, umur, diagnosis, intervensi, kat1, kat2, kat3, kat4, kat5, saranText;
    private LinearLayout ln1, ln2, ln3, ln4, ln5;
    private String getId, kesimpulan, saran, saran1, jenis1, jenis2, jenis3, jenis4, jenis5, jenis6;
    private String currentId, currentNama, currentKel, currentIbu, currentLahir, a1;
    private int usia, jumlahCek,cek;
    private String kategori [] = new String[10];
    private String penyimpangan [] = new String[10];
    private int ceklis[] = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getVal();
        deklarasi();
        rule();
        insertData();

        Button stimulasi = findViewById(R.id.stimulasi);
        if (kesimpulan.equals("Sesuai")||kesimpulan.equals("Penyimpangan")){
            stimulasi.setVisibility(View.GONE);
        } else {
            stimulasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DiagnosisKpspActivity.this, StimulasiActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void getVal(){
        Bundle extra = getIntent().getExtras();
        getId = extra.getString("id");
        usia = extra.getInt("umur");
        ceklis = extra.getIntArray("ceklis");
        kategori = extra.getStringArray("kategori");
    }

    private void deklarasi() {
        nama = findViewById(R.id.nama);
        ibu = findViewById(R.id.ibu);
        lahir = findViewById(R.id.lahir);
        umur = findViewById(R.id.usia);
        diagnosis = findViewById(R.id.kesimpulan);
        intervensi = findViewById(R.id.intervensi);
        kat1 = findViewById(R.id.cek1);
        kat2 = findViewById(R.id.cek2);
        kat3 = findViewById(R.id.cek3);
        kat4 = findViewById(R.id.cek4);
        kat5 = findViewById(R.id.cek5);
        ln1 = findViewById(R.id.ln1);
        ln2 = findViewById(R.id.ln2);
        ln3 = findViewById(R.id.ln3);
        ln4 = findViewById(R.id.ln4);
        ln5 = findViewById(R.id.ln5);
        saranText = findViewById(R.id.saran);

        mDbHelper = new TumbangDbHelper(this);
        bacaDb();
    }

    private void bacaDb(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM " + TumbangContract.dataAnak.TABLE__DATA+ " WHERE "
                        + TumbangContract.dataAnak.DATA_ID + " = ?",
                new String[]{getId});
        try {
            int idDb = cursor.getColumnIndex(TumbangContract.dataAnak.DATA_ID);
            int namaDb = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_NAME);
            int kelaminDb = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_KELAMIN);
            int ibuDb = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_IBU);
            int lahirDb = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_TANGGAL_LAHIR);

            while (cursor.moveToNext()) {
                currentId = cursor.getString(idDb);
                currentNama = cursor.getString(namaDb);
                currentKel = cursor.getString(kelaminDb);
                currentIbu = cursor.getString(ibuDb);
                currentLahir = cursor.getString(lahirDb);

                nama.setText(currentNama);
                ibu.setText(currentIbu);
                lahir.setText(currentLahir);
            }
        } finally {
            cursor.close();
        }
        umur.setText(String.valueOf(usia));
    }

    private void rule(){
        for (int i = 0; i < kategori.length; i++) {
            jumlahCek = jumlahCek + ceklis[i];
        }
        if (jumlahCek>=0 && jumlahCek<=6){kesimpulan = "Penyimpangan";}
        if (jumlahCek>6 && jumlahCek<=8){kesimpulan = "Meragukan";}
        if (jumlahCek>8){kesimpulan = "Sesuai";}

        if (kesimpulan=="Sesuai"){
            saran="Good Job Mom!! \n\nLanjutkan pemberian stimulasi sesuai dengan periode tumbuh kembang anak. " +
                    "\nIkutkan anak pada kegiatan posyandu secara rutin.";
        }
        if (kesimpulan=="Meragukan"){
            saran="Tumbuh kembang anak anda terganggu pada kategori :";
            saran1 = "Berikan stimulasi pada anak sesuai dengan periode tumbuh kembang anak dan sesuai dengan kategori" +
                    " gangguan diatas. \n\nLakukan tes KPSP satu minggu kemudian. \n\nApabila hasil tes Meragukan atau Penyimpangan" +
                    " segera rujuk ke rumah sakit.";
        }
        if (kesimpulan=="Penyimpangan"){
            saran="Tumbuh kembang anak mengalami penyimpangan pada kategori : ";
            saran1 = "Segera rujuk anak ke rumah sakit dan beritahu dokter kategori penyimpangan yang dialami oleh anak.";
        }

        if (usia>=11 && usia<=14){a1="U12";}
        if (usia>=15 && usia<=17){a1="U15";}
        if (usia>=18 && usia<=20){a1="U18";}
        if (usia>=21 && usia<=23){a1="U21";}
        if (usia>=24 && usia<=29){a1="U24";}
        if (usia>=30 && usia<=35){a1="U30";}
        if (usia>=36 && usia<=41){a1="U36";}
        if (usia>=42 && usia<=47){a1="U42";}
        if (usia>=48 && usia<=53){a1="U48";}
        if (usia>=54 && usia<=59){a1="U54";}
        if (usia>=60 && usia<=65){a1="U60";}
        if (usia>=66 && usia<=71){a1="U66";}
        if (usia==72){a1="U72";}

        if (a1.equals("U12")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U15")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis2="Gerak Halus";}
        if (a1.equals("U18")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U21")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U24")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U30")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U36")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis2="Gerak Halus";}
        if (a1.equals("U42")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U48")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U54")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis2="Gerak Halus";}
        if (a1.equals("U60")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U66")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis2="Gerak Halus";}
        if (a1.equals("U72")&&kesimpulan.equals("Meragukan")&&ceklis[0]==0){jenis4="Bahasa dan Bicara";}

        if (a1.equals("U12")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U15")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U18")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U21")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U24")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis2="Gerak Halus";}
        if (a1.equals("U30")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U36")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis2="Gerak Halus";}
        if (a1.equals("U42")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U48")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U54")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U60")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U66")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U72")&&kesimpulan.equals("Meragukan")&&ceklis[1]==0){jenis1="Gerak Kasar";}

        if (a1.equals("U12")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U15")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U18")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U21")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U24")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U30")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U36")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U42")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U48")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U54")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U60")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U66")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U72")&&kesimpulan.equals("Meragukan")&&ceklis[2]==0){jenis3="Sosialisasi dan Kemandirian";}

        if (a1.equals("U12")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U15")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U18")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U21")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis2="Gerak Halus";}
        if (a1.equals("U24")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U30")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U36")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U42")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U48")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U54")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U60")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis2="Gerak Halus";}
        if (a1.equals("U66")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U72")&&kesimpulan.equals("Meragukan")&&ceklis[3]==0){jenis2="Gerak Halus";}

        if (a1.equals("U12")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U15")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U18")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U21")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis2="Gerak Halus";}
        if (a1.equals("U24")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis5="Gerak Halus, Sosialisasi dan Kemandirian";}
        if (a1.equals("U30")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U36")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U42")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U48")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis2="Gerak Halus";}
        if (a1.equals("U54")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U60")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis2="Gerak Halus";}
        if (a1.equals("U66")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U72")&&kesimpulan.equals("Meragukan")&&ceklis[4]==0){jenis2="Gerak Halus";}

        if (a1.equals("U12")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U15")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U18")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U21")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U24")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U30")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U36")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U42")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis2="Gerak Halus";}
        if (a1.equals("U48")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis2="Gerak Halus";}
        if (a1.equals("U54")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U60")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U66")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U72")&&kesimpulan.equals("Meragukan")&&ceklis[5]==0){jenis4="Bahasa dan Bicara";}

        if (a1.equals("U12")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis2="Gerak Halus";}
        if (a1.equals("U15")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U18")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U21")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U24")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U30")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis2="Gerak Halus";}
        if (a1.equals("U36")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis2="Gerak Halus";}
        if (a1.equals("U42")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis2="Gerak Halus";}
        if (a1.equals("U48")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U54")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U60")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U66")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis2="Gerak Halus";}
        if (a1.equals("U72")&&kesimpulan.equals("Meragukan")&&ceklis[6]==0){jenis1="Gerak Kasar";}

        if (a1.equals("U12")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U15")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U18")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis2="Gerak Halus";}
        if (a1.equals("U21")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis2="Gerak Halus";}
        if (a1.equals("U24")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U30")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis2="Gerak Halus";}
        if (a1.equals("U36")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U42")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U48")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U54")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis2="Gerak Halus";}
        if (a1.equals("U60")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U66")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis2="Gerak Halus";}
        if (a1.equals("U72")&&kesimpulan.equals("Meragukan")&&ceklis[7]==0){jenis1="Gerak Kasar";}

        if (a1.equals("U12")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U15")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis2="Gerak Halus";}
        if (a1.equals("U18")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis5="Gerak Halus, Sosialisasi dan Kemandirian";}
        if (a1.equals("U21")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U24")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U30")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U36")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U42")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U48")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U54")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis2="Gerak Halus";}
        if (a1.equals("U60")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U66")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U72")&&kesimpulan.equals("Meragukan")&&ceklis[8]==0){jenis2="Gerak Halus";}

        if (a1.equals("U12")&&kesimpulan.equals("Meragukan")&&ceklis[9]==0){jenis2="Gerak Halus";}
        if (a1.equals("U15")&&kesimpulan.equals("Meragukan")&&ceklis[9]==0){jenis2="Gerak Halus";}
        if (a1.equals("U18")&&kesimpulan.equals("Meragukan")&&ceklis[9]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U21")&&kesimpulan.equals("Meragukan")&&ceklis[9]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U24")&&kesimpulan.equals("Meragukan")&&ceklis[9]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U30")&&kesimpulan.equals("Meragukan")&&ceklis[9]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U36")&&kesimpulan.equals("Meragukan")&&ceklis[9]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U54")&&kesimpulan.equals("Meragukan")&&ceklis[9]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U60")&&kesimpulan.equals("Meragukan")&&ceklis[9]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U66")&&kesimpulan.equals("Meragukan")&&ceklis[9]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U72")&&kesimpulan.equals("Meragukan")&&ceklis[9]==0){jenis4="Bahasa dan Bicara";}

        if (a1.equals("U12")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U15")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis2="Gerak Halus";}
        if (a1.equals("U18")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U21")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U24")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U30")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U36")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis2="Gerak Halus";}
        if (a1.equals("U42")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U48")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U54")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis2="Gerak Halus";}
        if (a1.equals("U60")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U66")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis2="Gerak Halus";}
        if (a1.equals("U72")&&kesimpulan.equals("Penyimpangan")&&ceklis[0]==0){jenis4="Bahasa dan Bicara";}

        if (a1.equals("U12")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U15")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U18")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U21")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U24")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis2="Gerak Halus";}
        if (a1.equals("U30")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U36")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis2="Gerak Halus";}
        if (a1.equals("U42")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U48")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U54")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U60")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U66")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U72")&&kesimpulan.equals("Penyimpangan")&&ceklis[1]==0){jenis1="Gerak Kasar";}

        if (a1.equals("U12")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U15")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U18")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U21")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U24")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U30")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U36")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U42")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U48")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U54")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U60")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U66")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U72")&&kesimpulan.equals("Penyimpangan")&&ceklis[2]==0){jenis3="Sosialisasi dan Kemandirian";}

        if (a1.equals("U12")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U15")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U18")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U21")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis2="Gerak Halus";}
        if (a1.equals("U24")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U30")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U36")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U42")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U48")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U54")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U60")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis2="Gerak Halus";}
        if (a1.equals("U66")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U72")&&kesimpulan.equals("Penyimpangan")&&ceklis[3]==0){jenis2="Gerak Halus";}

        if (a1.equals("U12")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U15")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U18")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U21")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis2="Gerak Halus";}
        if (a1.equals("U24")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis5="Gerak Halus, Sosialisasi dan Kemandirian";}
        if (a1.equals("U30")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U36")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U42")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U48")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis2="Gerak Halus";}
        if (a1.equals("U54")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U60")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis2="Gerak Halus";}
        if (a1.equals("U66")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U72")&&kesimpulan.equals("Penyimpangan")&&ceklis[4]==0){jenis2="Gerak Halus";}

        if (a1.equals("U12")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U15")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U18")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U21")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U24")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U30")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U36")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U42")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis2="Gerak Halus";}
        if (a1.equals("U48")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis2="Gerak Halus";}
        if (a1.equals("U54")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U60")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U66")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U72")&&kesimpulan.equals("Penyimpangan")&&ceklis[5]==0){jenis4="Bahasa dan Bicara";}

        if (a1.equals("U12")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis2="Gerak Halus";}
        if (a1.equals("U15")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U18")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U21")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U24")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U30")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis2="Gerak Halus";}
        if (a1.equals("U36")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis2="Gerak Halus";}
        if (a1.equals("U42")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis2="Gerak Halus";}
        if (a1.equals("U48")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U54")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U60")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U66")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis2="Gerak Halus";}
        if (a1.equals("U72")&&kesimpulan.equals("Penyimpangan")&&ceklis[6]==0){jenis1="Gerak Kasar";}

        if (a1.equals("U12")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U15")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U18")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis2="Gerak Halus";}
        if (a1.equals("U21")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis2="Gerak Halus";}
        if (a1.equals("U24")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U30")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis2="Gerak Halus";}
        if (a1.equals("U36")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U42")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U48")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U54")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis2="Gerak Halus";}
        if (a1.equals("U60")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U66")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis2="Gerak Halus";}
        if (a1.equals("U72")&&kesimpulan.equals("Penyimpangan")&&ceklis[7]==0){jenis1="Gerak Kasar";}

        if (a1.equals("U12")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U15")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis2="Gerak Halus";}
        if (a1.equals("U18")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis5="Gerak Halus, Sosialisasi dan Kemandirian";}
        if (a1.equals("U21")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U24")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U30")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U36")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U42")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U48")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U54")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis2="Gerak Halus";}
        if (a1.equals("U60")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U66")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U72")&&kesimpulan.equals("Penyimpangan")&&ceklis[8]==0){jenis2="Gerak Halus";}

        if (a1.equals("U12")&&kesimpulan.equals("Penyimpangan")&&ceklis[9]==0){jenis2="Gerak Halus";}
        if (a1.equals("U15")&&kesimpulan.equals("Penyimpangan")&&ceklis[9]==0){jenis2="Gerak Halus";}
        if (a1.equals("U18")&&kesimpulan.equals("Penyimpangan")&&ceklis[9]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U21")&&kesimpulan.equals("Penyimpangan")&&ceklis[9]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U24")&&kesimpulan.equals("Penyimpangan")&&ceklis[9]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U30")&&kesimpulan.equals("Penyimpangan")&&ceklis[9]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U36")&&kesimpulan.equals("Penyimpangan")&&ceklis[9]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U54")&&kesimpulan.equals("Penyimpangan")&&ceklis[9]==0){jenis4="Bahasa dan Bicara";}
        if (a1.equals("U60")&&kesimpulan.equals("Penyimpangan")&&ceklis[9]==0){jenis3="Sosialisasi dan Kemandirian";}
        if (a1.equals("U66")&&kesimpulan.equals("Penyimpangan")&&ceklis[9]==0){jenis1="Gerak Kasar";}
        if (a1.equals("U72")&&kesimpulan.equals("Penyimpangan")&&ceklis[9]==0){jenis4="Bahasa dan Bicara";}

        diagnosis.setText(kesimpulan);
        intervensi.setText(saran);

        if (kesimpulan.equals("Sesuai")){
            jenis1=null; jenis2=null; jenis3=null; jenis4=null; jenis5=null; jenis6=null;
            diagnosis.setTextColor(getResources().getColor(R.color.hijau));
            ln1.setVisibility(View.GONE);
            ln2.setVisibility(View.GONE);
            ln3.setVisibility(View.GONE);
            ln4.setVisibility(View.GONE);
            ln5.setVisibility(View.GONE);
            saranText.setVisibility(View.GONE);
        }else {
            diagnosis.setTextColor(getResources().getColor(R.color.merah));
            saranText.setText(saran1);
            if (jenis1==null){
                ln1.setVisibility(View.GONE);
            } else {
                kat1.setText(jenis1);
            }
            if (jenis2==null){
                ln2.setVisibility(View.GONE);
            } else {
                kat2.setText(jenis2);
            }
            if (jenis3==null){
                ln3.setVisibility(View.GONE);
            } else {
                kat3.setText(jenis3);
            }
            if (jenis4==null){
                ln4.setVisibility(View.GONE);
            } else {
                kat4.setText(jenis4);
            }
            if (jenis5==null){
                ln5.setVisibility(View.GONE);
            } else {
                kat5.setText(jenis5);
            }
        }
    }

    private void insertData(){
        Date dataId = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String tanggal = df.format(dataId);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(perkembangan.COLOUMN_TANGGAL_PERKEMBANGAN, tanggal);
        values.put(perkembangan.COLOUMN_ID_DATA, getId);
        values.put(perkembangan.COLOUMN_USIA_PERKEMBANGAN, usia);
        values.put(perkembangan.COLOUMN_PERKEMBANGAN_IDENTIFIKASI, kesimpulan);
        values.put(perkembangan.COLOUMN_GANGGUAN_1, jenis1);
        values.put(perkembangan.COLOUMN_GANGGUAN_2, jenis2);
        values.put(perkembangan.COLOUMN_GANGGUAN_3, jenis3);
        values.put(perkembangan.COLOUMN_GANGGUAN_4, jenis4);
        values.put(perkembangan.COLOUMN_GANGGUAN_5, jenis5);

        long newRowId = db.insert(perkembangan.TABLE_PERKEMBANGAN, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data berhasil disimpan ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            Intent i = new Intent(this,PerkembanganActivity.class);
            String input = "";
            i.putExtra("inp",input);
            startActivity(i);
        } else if(item.getItemId()==R.id.home){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
