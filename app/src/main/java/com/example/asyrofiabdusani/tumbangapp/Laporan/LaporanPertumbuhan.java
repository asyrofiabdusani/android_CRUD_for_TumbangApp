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
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract.pertumbuhan;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class LaporanPertumbuhan extends AppCompatActivity {
    private TumbangDbHelper mDbHelper;
    private TextView nama;
    private LaporanPertumbuhanAdapter mAdapter;
    private ArrayList<PertumbuhanList> list = new ArrayList<PertumbuhanList>();
    private ListView listView;
    private String getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_laporan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bacaDbAnak();
        laporanDb();

        listView = (ListView) findViewById(R.id.list_laporan);
        mAdapter = new LaporanPertumbuhanAdapter(this, list);
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

        Cursor cursor = db.rawQuery("SELECT * FROM " + pertumbuhan.TABLE_PERTUMBUHAN +
                        " WHERE " + pertumbuhan.COLOUMN_ID_DATA + " = ?" +
                        " ORDER BY " + pertumbuhan.COLOUMN_TANGGAL_PERTUMBUHAN + " DESC",
                        new String[]{getId});

        try {
            int tglColoumnIndex = cursor.getColumnIndex(pertumbuhan.COLOUMN_TANGGAL_PERTUMBUHAN);
            int usiaColumnIndex = cursor.getColumnIndex(pertumbuhan.COLOUMN_USIA_PERTUMBUHAN);
            int tbColumnIndex = cursor.getColumnIndex(pertumbuhan.COLOUMN_TINGGI_BADAN);
            int bbColumnIndex = cursor.getColumnIndex(pertumbuhan.COLOUMN_BERAT_BADAN);
            int lkaColoumnIndex = cursor.getColumnIndex(pertumbuhan.COLOUMN_LINGKAR_KEPALA);
            int imtColoumnIndex = cursor.getColumnIndex(pertumbuhan.COLOUMN_IMT);
            int kestbColumnIndex = cursor.getColumnIndex(pertumbuhan.COLOUMN_HASIL_TB);
            int kesbbColumnIndex = cursor.getColumnIndex(pertumbuhan.COLOUMN_HASIL_BB);
            int keslkaColoumnIndex = cursor.getColumnIndex(pertumbuhan.COLOUMN_HASIL_LKA);
            int kesimtColoumnIndex = cursor.getColumnIndex(pertumbuhan.COLOUMN_HASIL_IMT);

            while (cursor.moveToNext()) {
                String currentTgl = cursor.getString(tglColoumnIndex);
                String currentUsia = cursor.getString(usiaColumnIndex);
                String currentTb = cursor.getString(tbColumnIndex);
                String currentBb = cursor.getString(bbColumnIndex);
                String currentLka = cursor.getString(lkaColoumnIndex);
                String currentImt = cursor.getString(imtColoumnIndex);
                String currentKesTb = cursor.getString(kestbColumnIndex);
                String currentKesBb = cursor.getString(kesbbColumnIndex);
                String currentKesLka = cursor.getString(keslkaColoumnIndex);
                String currentKesImt = cursor.getString(kesimtColoumnIndex);

                list.add(new PertumbuhanList(currentTgl,currentUsia,currentTb,currentBb,currentLka,currentImt,currentKesTb,currentKesBb,
                        currentKesLka,currentKesImt));
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

class PertumbuhanList {
    private String mTanggal;
    private String mUsia;
    private String mTb;
    private String mBb;
    private String mLka;
    private String mImt;
    private String mKesTb;
    private String mKesBb;
    private String mKesLka;
    private String mKesImt;

    public PertumbuhanList(String defTanggal, String defUsia, String defTb, String defBb, String defLka, String defImt,
                           String defKesTb, String defKesBb, String defKesLka, String defKesImt){
        mTanggal = defTanggal; mUsia = defUsia; mTb = defTb; mBb = defBb; mLka = defLka; mImt = defImt;
        mKesTb = defKesTb; mKesBb = defKesBb; mKesLka = defKesLka; mKesImt = defKesImt;
    }
    public String getmTanggal() { return mTanggal;}
    public String getmUsia() { return mUsia;}
    public String getmTb() { return mTb;}
    public String getmBb() { return mBb;}
    public String getmLka() { return mLka;}
    public String getmImt() { return mImt;}
    public String getmKesTb() { return mKesTb;}
    public String getmKesBb() { return mKesBb;}
    public String getmKesLka() { return mKesLka;}
    public String getmKesImt() { return mKesImt;}
}

class LaporanPertumbuhanAdapter extends ArrayAdapter<PertumbuhanList> {

    public LaporanPertumbuhanAdapter(@NonNull Context context, ArrayList<PertumbuhanList> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_laporan_anak, parent, false);
        }
        PertumbuhanList list = (PertumbuhanList) getItem(position);

        TextView tanggal = (TextView) listItemView.findViewById(R.id.tanggal);
        tanggal.setText(list.getmTanggal());

        TextView usia = (TextView) listItemView.findViewById(R.id.usia);
        usia.setText(list.getmUsia());

        TextView tb = (TextView) listItemView.findViewById(R.id.tb);
        tb.setText(list.getmTb());

        TextView bb = (TextView) listItemView.findViewById(R.id.bb);
        bb.setText(list.getmBb());

        TextView lka = (TextView) listItemView.findViewById(R.id.lka);
        lka.setText(list.getmLka());

        TextView imt = (TextView) listItemView.findViewById(R.id.imt);
        imt.setText(list.getmImt());

        TextView kesTb = (TextView) listItemView.findViewById(R.id.kestb);
        kesTb.setText(list.getmKesTb());
        LinearLayout ln1 = (LinearLayout)listItemView.findViewById(R.id.ln1);
        if (list.getmKesTb().equals("Normal")){
            ln1.setBackgroundColor(Color.parseColor("#24b419"));
        }
        else {
            ln1.setBackgroundColor(Color.parseColor("#e50707"));
        }

        TextView kesBb = (TextView) listItemView.findViewById(R.id.kesbb);
        kesBb.setText(list.getmKesBb());
        LinearLayout ln2 = (LinearLayout)listItemView.findViewById(R.id.ln2);
        if (list.getmKesBb().equals("Gizi Baik")){
            ln2.setBackgroundColor(Color.parseColor("#24b419"));
        }
        else {
            ln2.setBackgroundColor(Color.parseColor("#e50707"));
        }

        TextView kesLka = (TextView) listItemView.findViewById(R.id.keslka);
        kesLka.setText(list.getmKesLka());
        LinearLayout ln3 = (LinearLayout)listItemView.findViewById(R.id.ln3);
        if (list.getmKesLka().equals("Normal")){
            ln3.setBackgroundColor(Color.parseColor("#24b419"));
        }
        else {
            ln3.setBackgroundColor(Color.parseColor("#e50707"));
        }

        TextView kesImt = (TextView) listItemView.findViewById(R.id.kesimt);
        kesImt.setText(list.getmKesImt());
        LinearLayout ln4 = (LinearLayout)listItemView.findViewById(R.id.ln4);
        if (list.getmKesImt().equals("Normal")){
            ln4.setBackgroundColor(Color.parseColor("#24b419"));
        }
        else {
            ln4.setBackgroundColor(Color.parseColor("#e50707"));
        }

        return listItemView;
    }

}


