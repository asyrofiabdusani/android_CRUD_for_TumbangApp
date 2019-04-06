package com.example.asyrofiabdusani.tumbangapp.Laporan;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;
import com.example.asyrofiabdusani.tumbangapp.Search.SearchLaporanPerkembangan;

import java.util.ArrayList;

public class LaporanPerkembanganListAnak extends AppCompatActivity {

    private TumbangDbHelper mDbHelper;
    private Cursor cursor;
    private DataAdapter mAdapter;
    private ArrayList<DataList> list = new ArrayList<DataList>();
    private ListView listView;
    private EditText searchEdit;
    private String getInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_anak);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getValue();
        searchEdit = (EditText)findViewById(R.id.search);
        searchEdit.setText(getInput);
        String searchString = searchEdit.getText().toString().trim();
        mDbHelper = new TumbangDbHelper(this);

        if (searchString.equals("")){
            dataDb();
        }else {
            DataSearch();
        }

        listView = (ListView) findViewById(R.id.data_list);
        mAdapter = new DataAdapter(this, list);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LaporanPerkembanganListAnak.this, LaporanPerkembangan.class);
                DataList pilihId = (DataList) mAdapter.getItem(position);
                intent.putExtra("id", pilihId.getmId());
                startActivity(intent);
            }
        });

        searchEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaporanPerkembanganListAnak.this, SearchLaporanPerkembangan.class);
                startActivity(intent);
            }
        });
    }
    private void getValue(){
        Bundle extra = getIntent().getExtras();
        getInput = extra.getString("input");
    }

    private void DataSearch(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM " + TumbangContract.dataAnak.TABLE__DATA+ " WHERE "
                        + TumbangContract.dataAnak.COLOUMN_NAME + " = ?",
                new String[]{getInput});

        try {
            int nameColoumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_NAME);
            int ibuColumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_IBU);
            int lahirColumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_TANGGAL_LAHIR);
            int idColumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.DATA_ID);
            int kelColoumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_KELAMIN);

            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColoumnIndex);
                String currentIbu = cursor.getString(ibuColumnIndex);
                String currentLahir = cursor.getString(lahirColumnIndex);
                String currentId = cursor.getString(idColumnIndex);
                String currentkel = cursor.getString(kelColoumnIndex);

                list.add(new DataList(currentName,currentIbu,currentLahir,currentId,currentkel));
            }
        } finally {
            cursor.close();
        }
    }

    private void dataDb() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                TumbangContract.dataAnak.COLOUMN_NAME,
                TumbangContract.dataAnak.COLOUMN_IBU,
                TumbangContract.dataAnak.COLOUMN_TANGGAL_LAHIR,
                TumbangContract.dataAnak.DATA_ID,
                TumbangContract.dataAnak.COLOUMN_KELAMIN
        };

        cursor = db.query(
                TumbangContract.dataAnak.TABLE__DATA,
                projection,
                null,
                null,
                null,
                null,
                TumbangContract.dataAnak.COLOUMN_NAME + " ASC");


        try {
            int nameColoumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_NAME);
            int ibuColumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_IBU);
            int lahirColumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_TANGGAL_LAHIR);
            int idColumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.DATA_ID);
            int kelColoumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_KELAMIN);

            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColoumnIndex);
                String currentIbu = cursor.getString(ibuColumnIndex);
                String currentLahir = cursor.getString(lahirColumnIndex);
                String currentId = cursor.getString(idColumnIndex);
                String currentkel = cursor.getString(kelColoumnIndex);

                list.add(new DataList(currentName,currentIbu,currentLahir,currentId,currentkel));
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

