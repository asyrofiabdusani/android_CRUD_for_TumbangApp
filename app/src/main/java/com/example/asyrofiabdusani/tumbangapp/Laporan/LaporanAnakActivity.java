package com.example.asyrofiabdusani.tumbangapp.Laporan;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asyrofiabdusani.tumbangapp.Data.*;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract.dataAnak;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract.pertumbuhan;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract.perkembangan;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.Pertumbuhan.*;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class LaporanAnakActivity extends AppCompatActivity {
    private TumbangDbHelper mDbHelper;
    private LaporanAnakAdapter mAdapter;
    private ArrayList<LaporanAnakList> list = new ArrayList<LaporanAnakList>();
    private ListView listView;
    private String currentDiag;
    private String [] id;
    private String [] idNext;
    private String [] nama;
    private String [] tgl;
    private String [] bb;
    private String [] tb;
    private String [] lk;
    private String [] imt;
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
        textView.setText("Diagnosis Pertumbuhan");

        Button kembang = findViewById(R.id.button);
        kembang.setText("data perkembangan");
        kembang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LaporanAnakActivity.this,LaporanAnakKembang.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void perkembanganDb() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM "
                + dataAnak.TABLE__DATA + " td "

                + " INNER JOIN " + pertumbuhan.TABLE_PERTUMBUHAN + " tt "
                + " ON td." + dataAnak.DATA_ID + " = tt." + pertumbuhan.COLOUMN_ID_DATA

                + " INNER JOIN ("
                + " SELECT "
                + pertumbuhan.COLOUMN_ID_DATA
                + ",MAX(" + pertumbuhan.COLOUMN_TANGGAL_PERTUMBUHAN + ") MAX_DATE"
                + " FROM " + pertumbuhan.TABLE_PERTUMBUHAN
                + " GROUP BY " + pertumbuhan.COLOUMN_ID_DATA
                + ") c ON "
                + "tt." + pertumbuhan.COLOUMN_ID_DATA + " = c." + pertumbuhan.COLOUMN_ID_DATA
                + " AND tt." + pertumbuhan.COLOUMN_TANGGAL_PERTUMBUHAN + " = c.MAX_DATE "

                + " ORDER BY td." + dataAnak.COLOUMN_NAME
                ,null);

        pj = cursor.getCount();
        id = new String[pj];
        idNext = new String[pj+1];
        nama = new String[pj];
        tgl = new String[pj];
        bb = new String[pj];
        tb = new String[pj];
        lk = new String[pj];
        imt = new String[pj];

        try {
            for (int i = 0; i <pj; i++){
                cursor.moveToNext();
                id[i] = cursor.getString(cursor.getColumnIndex(pertumbuhan.COLOUMN_ID_DATA));
                nama[i] = cursor.getString(cursor.getColumnIndex(dataAnak.COLOUMN_NAME));
                tgl[i] = cursor.getString(cursor.getColumnIndex(pertumbuhan.COLOUMN_TANGGAL_PERTUMBUHAN));
                bb[i] = cursor.getString(cursor.getColumnIndex(pertumbuhan.COLOUMN_HASIL_BB));
                tb[i] = cursor.getString(cursor.getColumnIndex(pertumbuhan.COLOUMN_HASIL_TB));
                lk[i] = cursor.getString(cursor.getColumnIndex(pertumbuhan.COLOUMN_HASIL_LKA));
                imt[i] = cursor.getString(cursor.getColumnIndex(pertumbuhan.COLOUMN_HASIL_IMT));
                idNext[i]=id[i];
            }
            idNext[pj]="dummy";

            for (int i = 0; i <pj; i++) {
                if (id[i].equals(idNext[i+1])) {
                } else {
                    list.add(new LaporanAnakList(nama[i], tgl[i], tb[i], bb[i], lk[i], imt[i], currentDiag));
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

class LaporanAnakList {
    private String mNama;
    private String mTanggal;
    private String mKategori1;
    private String mKategori2;
    private String mKategori3;
    private String mKategori4;
    private String mDiag;

    public LaporanAnakList(String defNama, String defTgl, String deftb, String defbb, String deflka, String defimt, String defDiag) {
        mNama = defNama;
        mTanggal = defTgl;
        mKategori1 = deftb;
        mKategori2 = defbb;
        mKategori3 = deflka;
        mKategori4 = defimt;
        mDiag = defDiag;
    }

    public String getmNama() {
        return mNama;
    }

    public String getmTanggal() {
        return mTanggal;
    }

    public String getmKategori1() {
        return mKategori1;
    }

    public String getmKategori2() {
        return mKategori2;
    }

    public String getmKategori3() {
        return mKategori3;
    }

    public String getmKategori4() {
        return mKategori4;
    }

    public String getmDiag() {
        return mDiag;
    }
}

class LaporanAnakAdapter extends ArrayAdapter<LaporanAnakList> {

    public LaporanAnakAdapter(@NonNull Context context, ArrayList<LaporanAnakList> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.laporan_anak, parent, false);
        }
        LaporanAnakList list = (LaporanAnakList) getItem(position);

        TextView nama = (TextView) listItemView.findViewById(R.id.nama);
        nama.setText(list.getmNama());

        TextView tanggal = (TextView) listItemView.findViewById(R.id.tanggal);
        tanggal.setText(list.getmTanggal());

        TextView tb = (TextView) listItemView.findViewById(R.id.tumbuh1);
        TextView bb = (TextView) listItemView.findViewById(R.id.tumbuh2);
        TextView lka = (TextView) listItemView.findViewById(R.id.tumbuh3);
        TextView imt = (TextView) listItemView.findViewById(R.id.tumbuh4);
        TextView kembang = (TextView) listItemView.findViewById(R.id.kembang);

        if (list.getmDiag()==null){
            kembang.setVisibility(View.GONE);
            tb.setText(list.getmKategori1());
            if (list.getmKategori1().equals("Normal")){
                tb.setBackgroundColor(Color.parseColor("#24b419"));
            }
            else {
                tb.setBackgroundColor(Color.parseColor("#e50707"));
            }
            bb.setText(list.getmKategori2());
            if (list.getmKategori2().equals("Gizi Baik")){
                bb.setBackgroundColor(Color.parseColor("#24b419"));
            }
            else {
                bb.setBackgroundColor(Color.parseColor("#e50707"));
            }
            lka.setText(list.getmKategori3());
            if (list.getmKategori3().equals("Normal")){
                lka.setBackgroundColor(Color.parseColor("#24b419"));
            }
            else {
                lka.setBackgroundColor(Color.parseColor("#e50707"));
            }
            imt.setText(list.getmKategori4());
            if (list.getmKategori4().equals("Normal")){
                imt.setBackgroundColor(Color.parseColor("#24b419"));
            }
            else {
                imt.setBackgroundColor(Color.parseColor("#e50707"));
            }
        }
        else {
            tb.setVisibility(View.GONE);
            bb.setVisibility(View.GONE);
            lka.setVisibility(View.GONE);
            imt.setVisibility(View.GONE);
            kembang.setText(list.getmDiag());
            if (list.getmDiag().equals("Sesuai")){
                kembang.setBackgroundColor(Color.parseColor("#24b419"));
            }
            else {
                kembang.setBackgroundColor(Color.parseColor("#e50707"));
            }
        }

        return listItemView;
    }

}

