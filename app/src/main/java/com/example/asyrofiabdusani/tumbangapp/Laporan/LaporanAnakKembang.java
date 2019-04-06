package com.example.asyrofiabdusani.tumbangapp.Laporan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class LaporanAnakKembang extends AppCompatActivity {
    private TumbangDbHelper mDbHelper;
    private LaporanAnakAdapter mAdapter;
    private ArrayList<LaporanAnakList> list = new ArrayList<LaporanAnakList>();
    private ListView listView;
    private String currentKesTb, currentKesBb, currentKesLka, currentKesImt;
    private String [] id;
    private String [] idNext;
    private String [] nama;
    private String [] tgl;
    private String [] diag;
    private int pj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_anak2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDbHelper = new TumbangDbHelper(this);
        listView = (ListView) findViewById(R.id.list_laporan);
        mAdapter = new LaporanAnakAdapter(this, list);
        listView.setAdapter(mAdapter);
        perkembanganDb();

        TextView textView = findViewById(R.id.judul);
        textView.setText("Diagnosis Perkembangan");

        Button kembang = findViewById(R.id.button);
        kembang.setText("data pertumbuhan");
        kembang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LaporanAnakKembang.this,LaporanAnakActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void perkembanganDb() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM "
                        + TumbangContract.dataAnak.TABLE__DATA + " td "

                        + " INNER JOIN " + TumbangContract.perkembangan.TABLE_PERKEMBANGAN+ " tt "
                        + " ON td." + TumbangContract.dataAnak.DATA_ID + " = tt." + TumbangContract.perkembangan.COLOUMN_ID_DATA

                        + " INNER JOIN ("

                        + " SELECT "
                        + TumbangContract.perkembangan.COLOUMN_ID_DATA
                        + ",MAX(" + TumbangContract.perkembangan.COLOUMN_TANGGAL_PERKEMBANGAN + ") MAX_DATE"
                        + " FROM " + TumbangContract.perkembangan.TABLE_PERKEMBANGAN
                        + " GROUP BY " + TumbangContract.perkembangan.COLOUMN_ID_DATA
                        + ") c ON "
                        + "tt." + TumbangContract.perkembangan.COLOUMN_ID_DATA + " = c." + TumbangContract.perkembangan.COLOUMN_ID_DATA
                        + " AND tt." + TumbangContract.perkembangan.COLOUMN_TANGGAL_PERKEMBANGAN + " = c.MAX_DATE "

                        + " ORDER BY td." + TumbangContract.dataAnak.COLOUMN_NAME
                ,null);

        pj = cursor.getCount();
        id = new String[pj];
        idNext = new String[pj+1];
        nama = new String[pj];
        tgl = new String[pj];
        diag = new String[pj];

        try {
            for (int i = 0; i <pj; i++){
                cursor.moveToNext();
                id[i] = cursor.getString(cursor.getColumnIndex(TumbangContract.perkembangan.COLOUMN_ID_DATA));
                nama[i] = cursor.getString(cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_NAME));
                tgl[i] = cursor.getString(cursor.getColumnIndex(TumbangContract.perkembangan.COLOUMN_TANGGAL_PERKEMBANGAN));
                diag[i] = cursor.getString(cursor.getColumnIndex(TumbangContract.perkembangan.COLOUMN_PERKEMBANGAN_IDENTIFIKASI));
                idNext[i]=id[i];
            }
            idNext[pj]="dummy";

            for (int i = 0; i <pj; i++) {
                if (id[i].equals(idNext[i+1])) {
                } else {
                    list.add(new LaporanAnakList(nama[i], tgl[i], currentKesTb, currentKesBb, currentKesLka, currentKesImt, diag[i]));
                }
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
