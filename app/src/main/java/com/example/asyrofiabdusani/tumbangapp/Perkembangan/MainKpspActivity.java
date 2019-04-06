package com.example.asyrofiabdusani.tumbangapp.Perkembangan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainKpspActivity extends AppCompatActivity {
    private Cursor cursor;
    private TextView nama;
    private TextView ibu;
    private TextView lahir;
    private TextView umur;
    private Button mulai;
    private TumbangDbHelper mDbHelper;
    private String getId, currentId, currentNama, currentIbu, currentLahir;
    private int usia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kpsp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        deklarasi();
        hitungUsia();

        mulai = findViewById(R.id.mulai);
        mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mulaiIntent = new Intent(MainKpspActivity.this, KpspActivity.class);
                mulaiIntent.putExtra("id", currentId);
                mulaiIntent.putExtra("umur", usia);
                if (usia<11 || usia >72){
                    Toast.makeText(MainKpspActivity.this, "Usia Tidak Sesuai", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(mulaiIntent);
                }
            }
        });
    }

    private void deklarasi() {
        nama = findViewById(R.id.nama);
        ibu = findViewById(R.id.ibu);
        lahir = findViewById(R.id.lahir);
        umur = findViewById(R.id.usia);

        mDbHelper = new TumbangDbHelper(this);
        bacaDb();
    }

    public void hitungUsia(){
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
        umur.setText(String.valueOf(usia));
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
            int ibuDb = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_IBU);
            int lahirDb = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_TANGGAL_LAHIR);

            while (cursor.moveToNext()) {
                currentId = cursor.getString(idDb);
                currentNama = cursor.getString(namaDb);
                currentIbu = cursor.getString(ibuDb);
                currentLahir = cursor.getString(lahirDb);

                nama.setText(currentNama);
                ibu.setText(currentIbu);
                lahir.setText(currentLahir);
            }
        } finally {
            cursor.close();
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

