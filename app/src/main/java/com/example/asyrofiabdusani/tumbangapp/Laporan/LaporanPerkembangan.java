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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract.perkembangan;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class LaporanPerkembangan extends AppCompatActivity {
    private TumbangDbHelper mDbHelper;
    private TextView nama;
    private LaporanPerkembanganAdapter mAdapter;
    private ArrayList<LaporanPerkembanganList> list = new ArrayList<LaporanPerkembanganList>();
    private ListView listView;
    private String getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_laporan_perkembangan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bacaDbAnak();
        laporanDb();

        listView = (ListView) findViewById(R.id.list_laporan);
        mAdapter = new LaporanPerkembanganAdapter(this, list);
        listView.setAdapter(mAdapter);
    }

    private void bacaDbAnak(){
        mDbHelper = new TumbangDbHelper(this);
        Bundle extra = getIntent().getExtras();
        getId = extra.getString("id");

        nama = (TextView) findViewById(R.id.nama);

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TumbangContract.dataAnak.TABLE__DATA+ " WHERE "
                        + TumbangContract.dataAnak.DATA_ID + " = ?",
                new String[]{getId});
        try {
            int namaDb = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_NAME);
            while (cursor.moveToNext()) {
                String currentNama = cursor.getString(namaDb);
                nama.setText(currentNama);
            }
        } finally {
            cursor.close();
        }
    }

    private void laporanDb() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + perkembangan.TABLE_PERKEMBANGAN +
                        " WHERE " + perkembangan.COLOUMN_ID_DATA + " = ?" +
                        " ORDER BY " + perkembangan.COLOUMN_TANGGAL_PERKEMBANGAN + " DESC",
                new String[]{getId});

        try {
            int tglColoumnIndex = cursor.getColumnIndex(perkembangan.COLOUMN_TANGGAL_PERKEMBANGAN);
            int usiaColumnIndex = cursor.getColumnIndex(perkembangan.COLOUMN_USIA_PERKEMBANGAN);
            int diagnosisColumnIndex = cursor.getColumnIndex(perkembangan.COLOUMN_PERKEMBANGAN_IDENTIFIKASI);
            int gang1 = cursor.getColumnIndex(perkembangan.COLOUMN_GANGGUAN_1);
            int gang2 = cursor.getColumnIndex(perkembangan.COLOUMN_GANGGUAN_2);
            int gang3 = cursor.getColumnIndex(perkembangan.COLOUMN_GANGGUAN_3);
            int gang4 = cursor.getColumnIndex(perkembangan.COLOUMN_GANGGUAN_4);
            int gang5 = cursor.getColumnIndex(perkembangan.COLOUMN_GANGGUAN_5);

            while (cursor.moveToNext()) {
                String currentTgl = cursor.getString(tglColoumnIndex);
                String currentUsia = cursor.getString(usiaColumnIndex);
                String currentDiag = cursor.getString(diagnosisColumnIndex);
                String currentGang1 = cursor.getString(gang1);
                String currentGang2 = cursor.getString(gang2);
                String currentGang3 = cursor.getString(gang3);
                String currentGang4 = cursor.getString(gang4);
                String currentGang5 = cursor.getString(gang5);

                list.add(new LaporanPerkembanganList(currentTgl,currentUsia,currentDiag,currentGang1,currentGang2,
                        currentGang3,currentGang4,currentGang5));
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

class LaporanPerkembanganList{
    private String mTanggal;
    private String mUsia;
    private String mDiagnosa;
    private String mKategori1;
    private String mKategori2;
    private String mKategori3;
    private String mKategori4;
    private String mKategori5;

    public LaporanPerkembanganList (String defTanggal,String defUsia,String defDiagnosis,String defKategori1,
                                    String defKategori2,String defKategori3,String defKategori4,String defKategori5){
        mTanggal = defTanggal; mUsia = defUsia; mDiagnosa = defDiagnosis; mKategori1 = defKategori1;
        mKategori2 = defKategori2; mKategori3 = defKategori3; mKategori4 = defKategori4; mKategori5 = defKategori5;
    }
    public String getmTanggal() { return mTanggal;}
    public String getmUsia() { return mUsia;}
    public String getmDiagnosa(){ return mDiagnosa;}
    public String getmKategori1() { return mKategori1;}
    public String getmKategori2() { return mKategori2;}
    public String getmKategori3() { return mKategori3;}
    public String getmKategori4() { return mKategori4;}
    public String getmKategori5() { return mKategori5;}
}

class LaporanPerkembanganAdapter extends ArrayAdapter<LaporanPerkembanganList> {

    public LaporanPerkembanganAdapter(@NonNull Context context, ArrayList<LaporanPerkembanganList> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_laporan_perkembangan, parent, false);
        }
        LaporanPerkembanganList list = (LaporanPerkembanganList) getItem(position);

        TextView tanggal = (TextView) listItemView.findViewById(R.id.tanggal);
        tanggal.setText(list.getmTanggal());

        TextView usia = (TextView) listItemView.findViewById(R.id.usia);
        usia.setText(list.getmUsia());

        TextView diagnosis = (TextView) listItemView.findViewById(R.id.diagnosis);
        diagnosis.setText(list.getmDiagnosa());
        if (list.getmDiagnosa().equals("Sesuai")){
            diagnosis.setBackgroundColor(Color.parseColor("#24b419"));
        } else {
            diagnosis.setBackgroundColor(Color.parseColor("#e50707"));
        }

        TextView gang1 = (TextView) listItemView.findViewById(R.id.jenis1);
        gang1.setText(list.getmKategori1());
        LinearLayout ln1 = (LinearLayout) listItemView.findViewById(R.id.ln1);
        if (list.getmKategori1()==null){
            ln1.setVisibility(View.GONE);
        } else {
            ln1.setVisibility(View.VISIBLE);
        }

        TextView gang2 = (TextView) listItemView.findViewById(R.id.jenis2);
        gang2.setText(list.getmKategori2());
        LinearLayout ln2 = (LinearLayout) listItemView.findViewById(R.id.ln2);
        if (list.getmKategori2()==null){
            ln2.setVisibility(View.GONE);
        } else {
            ln2.setVisibility(View.VISIBLE);
        }

        TextView gang3 = (TextView) listItemView.findViewById(R.id.jenis3);
        gang3.setText(list.getmKategori3());
        LinearLayout ln3 = (LinearLayout) listItemView.findViewById(R.id.ln3);
        if (list.getmKategori3()==null){
            ln3.setVisibility(View.GONE);
        } else {
            ln3.setVisibility(View.VISIBLE);
        }

        TextView gang4 = (TextView) listItemView.findViewById(R.id.jenis4);
        gang4.setText(list.getmKategori4());
        LinearLayout ln4 = (LinearLayout) listItemView.findViewById(R.id.ln4);
        if (list.getmKategori4()==null){
            ln4.setVisibility(View.GONE);
        } else {
            ln4.setVisibility(View.VISIBLE);
        }

        TextView gang5 = (TextView) listItemView.findViewById(R.id.jenis5);
        gang5.setText(list.getmKategori5());
        LinearLayout ln5 = (LinearLayout) listItemView.findViewById(R.id.ln5);
        if (list.getmKategori5()==null){
            ln5.setVisibility(View.GONE);
        } else {
            ln5.setVisibility(View.VISIBLE);
        }

        return listItemView;
    }

}
