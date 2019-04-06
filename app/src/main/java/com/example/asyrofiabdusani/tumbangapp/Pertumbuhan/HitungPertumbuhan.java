package com.example.asyrofiabdusani.tumbangapp.Pertumbuhan;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asyrofiabdusani.tumbangapp.Data.KeteranganActivity;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract.pertumbuhan;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import org.apache.commons.collections4.functors.IfClosure;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

public class HitungPertumbuhan extends AppCompatActivity {
    private Cursor cursor;
    private EditText bb,tb,lka;
    private TextView nama,ibu,lahir,umur,hasilbb,hasiltb,hasillka,hasilbbtb,textImt;
    private LinearLayout lnImt;
    private Button hitung;
    private TumbangDbHelper mDbHelper;
    private String getId, currentId, currentNama, currentKel, currentIbu, currentLahir;
    private String valBb, valTb, valLka, kesBb, kesTb, kesImt, kesLka,a1,a2,a3,imt;
    private int usia,a4;
    private double Bb, Tb, Lka, Imt;
    private Double [] varGbrLk = new Double[60];
    private Double [] varGkrLk = new Double[60];
    private Double [] varGbkLk = new Double[60];
    private Double [] varGbrPr = new Double[60];
    private Double [] varGkrPr = new Double[60];
    private Double [] varGbkPr = new Double[60];
    private Double [] varSpnLk = new Double[60];
    private Double [] varPenLk = new Double[60];
    private Double [] varNorLk = new Double[60];
    private Double [] varSpnPr = new Double[60];
    private Double [] varPenPr = new Double[60];
    private Double [] varNorPr = new Double[60];
    private Double [] varBlSkLk = new Double[60];
    private Double [] varBlKrLk = new Double[60];
    private Double [] varBlNrLk = new Double[60];
    private Double [] varBlSkPr = new Double[60];
    private Double [] varBlKrPr = new Double[60];
    private Double [] varBlNrPr = new Double[60];
    private Double [] varAnSkLk = new Double[72];
    private Double [] varAnKrLk = new Double[72];
    private Double [] varAnNrLk = new Double[72];
    private Double [] varAnGmLk = new Double[72];
    private Double [] varAnSkPr = new Double[72];
    private Double [] varAnKrPr = new Double[72];
    private Double [] varAnNrPr = new Double[72];
    private Double [] varAnGmPr = new Double[72];
    private Double [] varAnMikPr = new Double[60];
    private Double [] varAnMakPr = new Double[60];
    private Double [] varAnMikLk = new Double[60];
    private Double [] varAnMakLk = new Double[60];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_pertumbuhan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDbHelper = new TumbangDbHelper(this);
        bacaDb();
        hitungUsia();
        deklarasi();
        varNilai();

        hitung = findViewById(R.id.hitung);
        if (usia<=11||usia>=73){
            hitung.setText("Kembali");
            hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validasiIsi();
                }
            });
        }
    }

    private void bacaDb(){
        Bundle extra = getIntent().getExtras();
        getId = extra.getString("id");

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
            }
        } finally {
            cursor.close();
        }
    }

    private void hitungUsia(){
        Date lsDate = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("dd");
        SimpleDateFormat mf = new SimpleDateFormat("MM");
        SimpleDateFormat yf = new SimpleDateFormat("yyyy");

        Date stDate = null;
        try {
            stDate = format.parse(currentLahir);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String stDays= df.format(stDate);
        String stMonth = mf.format(stDate);
        String stYear = yf.format(stDate);
        String lsDays = df.format(lsDate);
        String lsMonth = mf.format(lsDate);
        String lsYear = yf.format(lsDate);

        int stDy = Integer.parseInt(stDays);
        int stMt = Integer.parseInt(stMonth);
        int stYr = Integer.parseInt(stYear);
        int lsDy = Integer.parseInt(lsDays);
        int lsMt = Integer.parseInt(lsMonth);
        int lsYr = Integer.parseInt(lsYear);

        int jmlYr = ((lsYr - stYr)*12);
        int jmlMt = (lsMt - stMt);
        int jmlDy;
        int jmlDays = (lsDy - stDy);
        if (jmlDays>=16){
            jmlDy = 1;
        }else {
            jmlDy = 0;
        }
        usia = (jmlYr + jmlMt + jmlDy);
    }

    private void deklarasi() {
        nama = findViewById(R.id.nama);
        ibu = findViewById(R.id.ibu);
        lahir = findViewById(R.id.lahir);
        umur = findViewById(R.id.usia);
        hasilbb = findViewById(R.id.hasilbb);
        hasiltb = findViewById(R.id.hasiltb);
        hasillka = findViewById(R.id.hasillka);
        hasilbbtb = findViewById(R.id.kesimpulan);
        bb = findViewById(R.id.bb);
        tb = findViewById(R.id.tb);
        lka = findViewById(R.id.lka);
        textImt = findViewById(R.id.imt);
        lnImt = findViewById(R.id.ln_imt);
        lnImt.setVisibility(View.GONE);

        nama.setText(currentNama);
        ibu.setText(currentIbu);
        lahir.setText(currentLahir);
        umur.setText(String.valueOf(usia));
        if (usia<=11||usia>=73){
            bb.setFocusableInTouchMode(false);
            hasilbb.setText("Usia Tidak Sesuai");
            hasilbb.setBackgroundColor(Color.parseColor("#e50707"));
            tb.setFocusableInTouchMode(false);
            hasiltb.setText("Usia Tidak Sesuai");
            hasiltb.setBackgroundColor(Color.parseColor("#e50707"));
            lka.setFocusableInTouchMode(false);
            hasillka.setText("Usia Tidak Sesuai");
            hasillka.setBackgroundColor(Color.parseColor("#e50707"));
        }
    }

    private void varNilai(){
        varGbrLk = new Double[]{2.1, 2.9, 3.8, 4.4, 4.9, 5.3, 5.7, 5.9, 6.2, 6.4, 6.6, 6.8, 6.9, 7.1,
                7.2, 7.4, 7.5, 7.7, 7.8, 8.0, 8.1, 8.2, 8.4, 8.5, 8.6, 8.8, 8.9, 9.0, 9.1, 9.2, 9.4, 9.5, 9.6, 9.7, 9.8,
                9.9, 10.0, 10.1, 10.2, 10.3, 10.4, 10.5, 10.6, 10.7, 10.8, 10.9, 11.0, 11.1, 11.2, 11.3, 11.4, 11.5,
                11.6, 11.7, 11.8, 11.9, 12.0, 12.1, 12.2, 12.3, 12.4};
        varGkrLk = new Double[]{2.5, 3.4, 4.3, 5.0, 5.6, 6.0, 6.4, 6.7, 6.9, 7.1, 7.4, 7.6, 7.7, 7.9,
                8.1, 8.3, 8.4, 8.6, 8.8, 8.9, 9.1, 9.2, 9.4, 9.5, 9.7, 9.8, 10.0, 10.1, 10.2, 10.4, 10.5, 10.7, 10.8,
                10.9, 11.0, 11.2, 11.3, 11.4, 11.5, 11.6, 11.8, 11.9, 12.0, 12.1, 12.2, 12.4, 12.5, 12.6, 12.7, 12.8,
                12.9, 13.1, 13.2, 13.3, 13.4, 13.5, 13.6, 13.7, 13.8, 14.0, 14.1};
        varGbkLk = new Double[]{4.4, 5.5, 7.1, 8.0, 8.7, 9.3, 9.8, 10.3, 10.7, 11.0, 11.4, 11.7, 12.0,
                12.3, 12.6, 12.8, 13.1, 13.4, 13.7, 13.9, 14.2, 14.5, 14.7, 15.0, 15.3, 15.5, 15.8, 16.1, 16.3, 16.6, 16.9,
                17.1, 17.4, 17.6, 17.8, 18.1, 18.3, 18.6, 18.8, 19.0, 19.3, 19.5, 19.7, 20.0, 20.2, 20.5, 20.7, 20.9,
                21.2, 21.4, 21.7, 21.9, 22.2, 22.4, 22.7, 22.9, 23.2, 23.4, 23.7, 23.9, 24.2};

        varGbrPr = new Double[]{2.0,2.7,3.4,4.0,4.4,4.8,5.1,5.3,5.6,5.8,5.9,6.1,6.3,6.4,6.6,6.7,6.9,7.0,7.2,
                7.3,7.5,7.6,7.8,7.9,8.1,8.2,8.4,8.5,8.6,8.8,8.9,9.0,9.1,9.3,9.4,9.5,9.6,9.7,9.8,9.9,10.1,10.2,10.3,
                10.4,10.5,10.6,10.7,10.8,10.9,11.0,11.1,11.2,11.3,11.4,11.5,11.6,11.7,11.8,11.9,12.0,12.1};
        varGkrPr = new Double[]{2.4,3.2,3.9,4.5,5.0,5.4,5.7,6.0,6.3,6.5,6.7,6.9,7.0,7.2,7.4,7.6,7.7,7.9,8.1,8.2,8.4,
                8.6,8.7,8.9,9.0,9.2,9.4,9.5,9.7,9.8,10.0,10.1,10.3,10.4,10.5,10.7,10.8,10.9,11.1,11.2,11.3,11.5,11.6,11.7,
                11.8,12.0,12.1,12.2,12.3,12.4,12.6,12.7,12.8,12.9,13.0,13.2,13.3,13.4,13.5,13.6,13.7};
        varGbkPr = new Double[]{4.2,5.5,6.6,7.5,8.2,8.8,9.3,9.8,10.2,10.5,10.9,11.2,11.5,11.8,12.1,12.4,12.6,12.9,
                13.2,13.5,13.7,14.0,14.3,14.6,14.8,15.1,15.4,15.7,16.0,16.2,16.5,16.8,17.1,17.3,17.6,17.9,18.1,18.4,18.7,
                19.0,19.2,19.5,19.8,20.1,20.4,20.7,20.9,21.2,21.5,21.8,22.1,22.4,22.6,22.9,23.2,23.5,23.8,24.1,24.2,24.6,24.9};

        varSpnLk = new Double[]{44.2,48.9,52.4,55.3,57.6,59.6,61.2,62.7,64.0,65.2,66.4,67.6,68.6,69.6,70.6,71.6,72.5,73.3,
                74.2,75.0,75.8,76.5,77.2,78.0,78.7,78.6,79.3,79.9,80.5,81.1,81.7,82.3,82.8,83.4,83.9,84.4,85.0,85.5,86.0,
                86.5,87.0,87.5,88.0,88.4,88.9,89.4,89.8,90.3,90.7,91.2,91.6,92.1,92.5,93.0,93.4,93.9,94.3,94.7,95.2,95.6,96.1};
        varPenLk = new Double[]{46.1,50.8,54.5,57.3,59.7,61.7,63.3,64.8,66.2,67.5,68.7,69.9,71.0,72.1,73.1,74.1,75.0,
                76.0,76.9,77.7,78.6,79.4,80.2,81.0,81.7,81.7,82.5,83.1,83.8,84.5,85.1,85.7,86.4,86.9,87.5,88.1,88.7,
                89.2,89.9,90.3,90.9,91.4,91.9,92.4,93.0,93.5,94.0,94.4,94.9,95.4,95.9,96.4,96.9,97.4,97.8,98.3,98.8,99.3,99.7,100.2,100.7};
        varNorLk = new Double[]{53.7,58.6,62.4,65.5,68.0,70.1,71.9,73.5,75.0,76.5,77.9,79.2,80.5,81.8,83.0,84.2,85.4,86.5,87.7,
                88.8,89.8,90.9,91.9,92.9,93.9,94.2,95.2,96.1,97.0,97.9,98.7,99.6,100.4,101.2,102.0,102.7,103.5,104.2,105.0,105.7,106.4,
                107.1,107.8,108.5,109.1,109.8,110.4,111.1,111.7,112.4,113.0,113.6,114.2,114.9,115.5,116.1,116.7,117.4,118.0,118.6,119.2};

        varSpnPr = new Double[]{43.6,47.8,51.0,53.5,55.6,57.4,58.9,60.3,61.7,62.9,64.1,65.2,66.3,67.3,68.3,69.3,70.2,71.1,72.0,72.8,
                73.7,74.5,75.2,76.0,76.7,76.8,77.5,78.1,78.8,79.5,80.1,80.7,81.3,81.9,82.5,83.1,83.6,84.2,84.7,85.3,
                85.8,86.3,86.8,87.4,87.9,88.4,88.9,89.3,89.8,90.3,90.7,91.2,91.7,92.1,92.6,93.0,93.4,93.9,94.3,94.7,95.2};
        varPenPr = new Double[]{45.4,49.8,53.0,55.6,57.8,59.6,61.2,62.7,64.0,65.3,66.5,67.7,68.9,70.0,71.0,72.0,73.0,
                74.0,74.9,75.8,76.7,77.5,78.4,79.2,80.0,80.0,80.8,81.5,82.2,82.9,83.6,84.3,84.9,85.6,86.2,86.8,87.4,88.0,
                88.6,89.2,89.8,90.4,90.9,91.5,92.0,92.5,93.1,93.6,94.1,94.6,95.1,95.6,96.1,96.6,97.1,97.6,98.1,98.5,99.0,99.5,99.9};
        varNorPr = new Double[]{52.9,57.6,61.1,64.0,66.4,68.5,70.3,71.9,73.5,75.0,76.4,77.8,79.2,80.5,81.7,83.0,
                84.2,85.4,86.5,87.6,88.7,89.8,90.8,91.9,92.9,93.1,94.1,95.0,96.0,96.9,97.7,98.6,99.4,100.3,101.1,101.9,
                102.7,103.4,104.2,105.0,105.7,106.4,107.2,107.9,108.6,109.3,110.0,110.7,111.3,112.0,112.7,113.3,114.0,114.6,115.2,115.9,
                116.5,117.1,117.7,118.3,118.9};

        varBlSkLk = new Double[]{10.2,11.3,12.5,13.1,13.4,13.5,13.6,13.7,13.6,13.6,13.5,13.4,13.4,13.3,13.2,13.1,13.1,13.0,12.9,
                12.9,12.8,12.8,12.7,12.7,12.7,12.9,12.8,12.8,12.7,12.7,12.7,12.6,12.6,12.5,12.5,12.5,12.4,12.4,12.4,12.3,12.3,12.3,
                12.2,12.2,12.2,12.2,12.2,12.1,12.1,12.1,12.1,12.1,12.1,12.0,12.0,12.0,12.0,12.0,12.0,12.0,12.0,12.0};
        varBlKrLk = new Double[]{11.1,12.4,13.7,14.3,14.5,14.7,14.7,14.8,14.7,14.7,14.6,14.5,14.4,14.3,14.2,14.1,14.0,13.9,13.9,
                13.8,13.7,13.7,13.6,13.6,13.6,13.8,13.8,13.7,13.7,13.6,13.6,13.6,13.5,13.5,13.5,13.4,13.4,13.4,13.3,13.3,13.3,13.2,13.2,
                13.2,13.2,13.1,13.1,13.1,13.1,13.1,13.0,13.0,13.0,13.0,13.0,13.0,13.0,12.9,12.9,12.9,12.9,12.9};
        varBlNrLk = new Double[]{16.3,17.8,19.4,20.0,20.3,20.5,20.5,20.5,20.4,20.3,20.1,20.0,19.8,19.7,19.5,19.4,19.3,19.1,19.0,
                18.9,18.8,18.7,18.7,18.6,18.5,18.9,18.8,18.8,18.7,18.7,18.6,18.6,18.5,18.5,18.5,18.4,18.4,18.4,18.3,18.3,18.3,18.2,18.2,
                18.2,18.2,18.2,18.2,18.2,18.2,18.2,18.2,18.2,18.2,18.2,18.2,18.2,18.2,18.2,18.2,18.3,18.3,18.3};

        varBlSkPr = new Double[]{10.8,11.8,12.4,12.7,12.9,13.0,13.0,13.0,12.9,12.9,12.8,12.7,12.6,12.6,12.5,12.4,12.4,12.3,12.3,12.2,
                12.2,12.2,12.2,12.1,12.4,12.4,12.3,12.3,12.3,12.3,12.3,12.2,12.2,12.2,12.2,12.1,12.1,12.1,12.1,12.0,12.0,12.0,12.0,11.9,
                11.9,11.9,11.9,11.8,11.8,11.8,11.8,11.8,11.7,11.7,11.7,11.7,11.7,11.7,11.7,11.6,11.6};
        varBlKrPr = new Double[]{12.0,13.0,13.6,13.9,14.1,14.1,14.2,14.1,14.1,14.0,13.9,13.8,13.7,13.6,13.5,13.5,13.4,13.3,13.3,13.2,
                13.2,13.1,13.1,13.1,13.3,13.3,13.3,13.3,13.3,13.2,13.2,13.2,13.2,13.1,13.1,13.1,13.1,13.1,13.0,13.0,13.0,13.0,12.9,12.9,
                12.9,12.9,12.9,12.8,12.8,12.8,12.8,12.8,12.8,12.7,12.7,12.7,12.7,12.7,12.7,12.7,12.7};
        varBlNrPr = new Double[]{17.5,19.0,19.7,20.0,20.2,20.3,20.3,20.2,20.1,19.9,19.8,19.6,19.5,19.3,19.2,19.1,18.9,18.8,18.8,18.7,
                18.6,18.5,18.5,18.4,18.7,18.7,18.7,18.6,18.6,18.6,18.5,18.5,18.5,18.5,18.5,18.4,18.4,18.4,18.4,18.4,18.4,18.4,18.4,18.4,
                18.5,18.5,18.5,18.5,18.5,18.5,18.6,18.6,18.6,18.6,18.7,18.7,18.7,18.7,18.8,18.8,18.8};

        varAnSkLk = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                12.1,12.1,12.1,12.1,12.1,12.1,12.1,12.1,12.1,12.1,12.1,12.1};
        varAnKrLk = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                13.0,13.0,13.0,13.0,13.0,13.0,13.0,13.0,13.0,13.0,13.0,13.0};
        varAnKrLk = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                16.6,16.6,16.7,16.7,16.7,16.7,16.7,16.7,16.7,16.7,16.7,16.8};
        varAnGmLk = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                18.3,18.3,18.3,18.3,18.3,18.4,18.4,18.4,18.4,18.5,18.5,18.5};

        varAnSkPr = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                11.8,11.8,11.8,11.8,11.7,11.7,11.7,11.7,11.7,11.7,11.7,11.7};
        varAnKrPr = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                12.7,12.7,12.7,12.7,12.7,12.7,12.7,12.7,12.7,12.7,12.7,12.7};
        varAnNrPr = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                16.9,16.9,16.9,16.9,16.9,16.9,16.9,17.0,17.0,17.0,17.0,17.0};
        varAnGmPr = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
                18.9,18.9,18.9,18.9,19.0,19.0,19.0,19.1,19.1,19.1,19.2,19.2};

        varAnMikLk = new Double[]{31.9, 34.9, 36.8, 38.1, 39.2, 40.1, 40.9, 41.5, 42.0, 42.5, 42.9, 43.2, 43.5,
                43.8, 44.0, 44.2, 44.4, 44.6, 44.7, 44.9, 45.0, 45.2, 45.3, 45.4, 45.5, 45.6, 45.8, 45.9, 46.0,
                46.1, 46.1, 46.2, 46.3, 46.4, 46.5, 46.6, 46.6, 46.7, 46.8, 46.8, 46.9, 46.9, 47.0, 47.0, 47.1,
                47.1, 47.2, 47.2, 47.3, 47.3, 47.4, 47.4, 47.5, 47.5, 47.5, 47.6, 47.6, 47.6, 47.7, 47.7, 47.7};
        varAnMakLk = new Double[]{37.0, 39.6, 41.5, 42.9, 44.0, 45.0, 45.8, 46.4, 47.0, 47.5, 47.9, 48.3, 48.6,
                48.9, 49.2, 49.4, 49.6, 49.8, 50.0, 50.2, 50.4, 50.5, 50.7, 50.8, 51.0, 51.1, 51.2, 51.4, 51.5,
                51.6, 51.7, 51.8, 51.9, 52.0, 52.1, 52.2, 52.3, 52.4, 52.5, 52.5, 52.6, 52.7, 52.8, 52.8, 52.9,
                53.0, 53.0, 53.1, 53.1, 53.2, 53.2, 53.3, 53.4, 53.4, 53.5, 53.5, 53.5, 53.6, 53.6, 53.7, 53.7};
        varAnMikPr = new Double[]{31.5, 34.2, 35.8, 37.1, 38.1, 38.9, 39.6, 40.2, 40.7, 41.2, 41.5, 41.9, 42.2,
                42.4, 42.7, 42.9, 43.1, 43.3, 43.5, 43.6, 43.8, 44.0, 44.1, 44.3, 44.4, 44.5, 44.7, 44.8, 44.9,
                45.0, 45.1, 45.2, 45.3, 45.4, 45.5, 45.6, 45.7, 45.8, 45.8, 45.9, 46.0, 46.1, 46.1, 46.2, 46.3,
                46.3, 46.4, 46.4, 46.5, 46.5, 46.6, 46.7, 46.7, 46.8, 46.8, 46.9, 46.9, 46.9, 47.0, 47.0, 47.1};
        varAnMakPr = new Double[]{36.2, 38.9, 40.7, 42.0, 43.1, 44.0, 44.8, 45.5, 46.0, 46.5, 46.9, 47.3, 47.6,
                47.9, 48.2, 48.4, 48.6, 48.8, 49.0, 49.2, 49.4, 49.5, 49.7, 49.8, 50.0, 50.1, 50.3, 50.4, 50.5,
                50.6, 50.7, 50.9, 51.0, 51.1, 51.2, 51.2, 51.3, 51.4, 51.5, 51.6, 51.7, 51.7, 51.8, 51.9, 51.9,
                52.0, 52.1, 52.1, 52.2, 52.2, 52.3, 52.3, 52.4, 52.4, 52.5, 52.5, 52.6, 52.6, 52.7, 52.7, 52.8};

    }

    private void rule(){
        valBb = bb.getText().toString().trim();
        Bb = Double.parseDouble(valBb);
        valTb = tb.getText().toString().trim();
        Tb = Double.parseDouble(valTb);
        valLka = lka.getText().toString().trim();
        Lka = Double.parseDouble(valLka);

        if (usia>=12){a1="balita";}
        if (a1.equals("balita")&&usia<=60){a2="balita";}
        if (usia>=61){a1="anak";}
        if (a1.equals("anak")&&usia<=72){a2="anak";}
        if (a2.equals("balita")&&currentKel.equals("1")){a3="balita laki-laki";}
        if (a2.equals("balita")&&currentKel.equals("0")){a3="balita perempuan";}
        if (a2.equals("anak")&&currentKel.equals("1")){a3="anak laki-laki";}
        if (a2.equals("anak")&&currentKel.equals("0")) {a3="anak perempuan";}
        if (Bb == 0 || Tb ==0 ){ Imt = 0;}
        if (Bb != 0 && Tb != 0 ){ Imt = (Bb/((Tb/100)*(Tb/100)));}

        if (a3.equals("balita laki-laki") && Bb < varGbrLk[usia]){kesBb = "Gizi Buruk";}
        if (a3.equals("balita laki-laki") && Bb >= varGbrLk[usia] && Bb < varGkrLk[usia]) {kesBb = "Gizi Kurang";}
        if (a3.equals("balita laki-laki") && Bb >=varGkrLk[usia] && Bb <= varGbkLk[usia]) {kesBb = "Gizi Baik";}
        if (a3.equals("balita laki-laki") && Bb > varGbkLk[usia]){kesBb = "Gizi Lebih";}

        if (a3.equals("balita perempuan") && Bb < varGbrPr[usia]) {kesBb = "Gizi Buruk";}
        if (a3.equals("balita perempuan") && Bb >= varGbrPr[usia]&& Bb < varGkrPr[usia]) {kesBb = "Gizi Kurang";}
        if (a3.equals("balita perempuan") && Bb >=varGkrPr[usia]&& Bb <= varGbkPr[usia]) {kesBb = "Gizi Baik";}
        if (a3.equals("balita perempuan") && Bb > varGbkPr[usia]) {kesBb = "Gizi Lebih";}

        if (a3.equals("balita laki-laki") && Tb < varSpnLk[usia]){kesTb = "Sangat Pendek";}
        if (a3.equals("balita laki-laki") && Tb >= varSpnLk[usia] && Tb < varPenLk[usia]){ kesTb = "Pendek";}
        if (a3.equals("balita laki-laki") && Tb >= varPenLk[usia] && Tb <= varNorLk[usia]){kesTb = "Normal";}
        if (a3.equals("balita laki-laki") && Tb > varNorLk[usia]) {kesTb = "Tinggi";}

        if (a3.equals("balita perempuan")&& Tb < varSpnPr[usia]){kesTb = "Sangat Pendek";}
        if (a3.equals("balita perempuan")&& Tb >= varSpnPr[usia] && Tb < varPenPr[usia]){kesTb = "Pendek";}
        if (a3.equals("balita perempuan")&& Tb >= varPenPr[usia] && Tb <= varNorPr[usia]){kesTb = "Normal";}
        if (a3.equals("balita perempuan")&& Tb > varNorPr[usia]){kesTb = "Tinggi";}

        if (a3.equals("balita laki-laki") && Lka < varAnMikLk[usia]){kesLka = "Mikrosefal";}
        if (a3.equals("balita laki-laki") && Lka >= varAnMikLk[usia] && Lka <= varAnMakLk[usia]){kesLka = "Normal";}
        if (a3.equals("balita laki-laki") && Lka > varAnMakLk[usia]){kesLka = "Makrosefal";}

        if (a3.equals("balita perempuan") && Lka < varAnMikPr[usia]){kesLka = "Mikrosefal";}
        if (a3.equals("balita perempuan") && Lka >= varAnMikPr[usia] && Lka <= varAnMakPr[usia]){kesLka = "Normal";}
        if (a3.equals("balita perempuan") && Lka > varAnMakPr[usia]){kesLka = "Makrosefal";}

        if (a3.equals("balita laki-laki")&& Imt < varBlSkLk[usia]) {kesImt = "Sangat Kurus";}
        if (a3.equals("balita laki-laki")&& Imt >= varBlSkLk[usia] && Imt < varBlKrLk[usia]) {kesImt = "Kurus";}
        if (a3.equals("balita laki-laki")&& Imt >= varBlKrLk[usia] && Imt <= varBlNrLk[usia]) {kesImt = "Normal";}
        if (a3.equals("balita laki-laki")&& Imt > varBlNrLk[usia]) {kesImt = "Gemuk";}

        if (a3.equals("balita perempuan")&& Imt < varBlSkPr[usia]) {kesImt = "Sangat Kurus";}
        if (a3.equals("balita perempuan")&& Imt >= varBlSkPr[usia] && Imt < varBlKrPr[usia]) {kesImt = "Kurus";}
        if (a3.equals("balita perempuan")&& Imt >= varBlKrPr[usia] && Imt <= varBlNrPr[usia]) {kesImt = "Normal";}
        if (a3.equals("balita perempuan")&& Imt > varBlNrPr[usia]) {kesImt = "Gemuk";}

        if (a3.equals("anak laki-laki")){
            kesTb="Usia Tidak Sesuai";kesBb="Usia Tidak Sesuai";kesLka="Usia Tidak Sesuai";
            if (Imt < varAnSkLk[usia]) {
                kesImt = "Sangat Kurus";
            } else if (Imt < varAnKrLk[usia]) {
                kesImt = "Kurus";
            } else if (Imt <= varAnNrLk[usia]) {
                kesImt = "Normal";
            } else if (Imt <= varAnGmLk[usia]) {
                kesImt = "Gemuk";
            } else {
                kesImt = "Obesitas";
            }
        }

        if (a3.equals("anak perempuan")){
            kesTb="Usia Tidak Sesuai";kesBb="Usia Tidak Sesuai";kesLka="Usia Tidak Sesuai";
            if (Imt < varAnSkPr[usia]) {
                kesImt = "Sangat Kurus";
            } else if (Imt < varAnKrPr[usia]) {
                kesImt = "Kurus";
            } else if (Imt <= varAnNrPr[usia]) {
                kesImt = "Normal";
            } else if (Imt <= varAnGmPr[usia]) {
                kesImt = "Gemuk";
            } else {
                kesImt = "Obesitas";
            }
        }

        if ((a1.equals("balita") || a1.equals("anak")) && Bb == 0 ) { kesBb = "Tidak Dihitung";}
        if ((a1.equals("balita") || a1.equals("anak")) && Tb == 0 ) { kesTb = "Tidak Dihitung";}
        if ((a1.equals("balita") || a1.equals("anak")) && Lka ==0 ) { kesLka = "Tidak Dihitung";}
        if ((a1.equals("balita") || a1.equals("anak")) && Imt == 0 ) { kesImt =  "Tidak Dihitung";}


        DecimalFormat format = new DecimalFormat("#.##");
        imt = format.format(Imt);
        lnImt.setVisibility(View.VISIBLE);
        textImt.setText(String.valueOf(imt));
        hasiltb.setText(String.valueOf(kesTb));
        if (kesTb.equals("Normal")){
            hasiltb.setBackgroundColor(Color.parseColor("#24b419"));
        }
        else {
            hasiltb.setBackgroundColor(Color.parseColor("#e50707"));
        }
        hasilbb.setText(String.valueOf(kesBb));
        if (kesBb.equals("Gizi Baik")){
            hasilbb.setBackgroundColor(Color.parseColor("#24b419"));
        }
        else {
            hasilbb.setBackgroundColor(Color.parseColor("#e50707"));
        }
        hasillka.setText(String.valueOf(kesLka));
        if (kesLka.equals("Normal")){
            hasillka.setBackgroundColor(Color.parseColor("#24b419"));
        }
        else {
            hasillka.setBackgroundColor(Color.parseColor("#e50707"));
            hasillka.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HitungPertumbuhan.this, KeteranganActivity.class);
                    if (kesLka.equals("Mikrosefal")){
                        intent.putExtra("ket","Mikrosefal adalah ukuran lingkar kepala lebih kecil dari ukuran normal");
                        startActivity(intent);
                    } else if (kesLka.equals("Makrosefal")){
                        intent.putExtra("ket","Makrosefal adalah ukuran lingkar kepala lebih besar dari ukuran normal");
                        startActivity(intent);
                    } else {

                    }
                }
            });
        }
        hasilbbtb.setText(String.valueOf(kesImt));
        if (kesImt.equals("Normal")){
            hasilbbtb.setBackgroundColor(Color.parseColor("#24b419"));
        }
        else {
            hasilbbtb.setBackgroundColor(Color.parseColor("#e50707"));
        }

        insertData();
        hitung.setText("Kembali");
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void validasiIsi() {
        if (tb.getText().toString().trim().length() == 0) {
            tb.setError("Harus Diisi");
        } else if (bb.getText().toString().trim().length() == 0) {
            bb.setError("Harus Diisi");
        } else if (lka.getText().toString().trim().length() == 0) {
            lka.setError("Harus Diisi");
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(HitungPertumbuhan.this);
            builder.setTitle("Apakah Anda Yakin Akan Memproses Data");
            builder.setCancelable(false);

            builder.setPositiveButton(
                    "Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            rule();
                        }
                    }
            );
            builder.setNegativeButton(
                    "Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }
            );
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    private void insertData(){
        Date dataId = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String tanggal = df.format(dataId);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(pertumbuhan.COLOUMN_TANGGAL_PERTUMBUHAN, tanggal);
        values.put(pertumbuhan.COLOUMN_ID_DATA, getId);
        values.put(pertumbuhan.COLOUMN_USIA_PERTUMBUHAN, usia);
        values.put(pertumbuhan.COLOUMN_TINGGI_BADAN, valTb);
        values.put(pertumbuhan.COLOUMN_BERAT_BADAN, valBb);
        values.put(pertumbuhan.COLOUMN_LINGKAR_KEPALA, valLka);
        values.put(pertumbuhan.COLOUMN_IMT, imt);
        values.put(pertumbuhan.COLOUMN_HASIL_TB, kesTb);
        values.put(pertumbuhan.COLOUMN_HASIL_BB, kesBb);
        values.put(pertumbuhan.COLOUMN_HASIL_LKA, kesLka);
        values.put(pertumbuhan.COLOUMN_HASIL_IMT, kesImt);


        long newRowId = db.insert(pertumbuhan.TABLE_PERTUMBUHAN, null, values);

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
            finish();
        } else if(item.getItemId()==R.id.home){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

