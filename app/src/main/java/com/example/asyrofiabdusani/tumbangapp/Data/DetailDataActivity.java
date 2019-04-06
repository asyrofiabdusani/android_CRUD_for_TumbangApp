package com.example.asyrofiabdusani.tumbangapp.Data;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract.dataAnak;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;
import com.example.asyrofiabdusani.tumbangapp.Search.SearchActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailDataActivity extends AppCompatActivity {
    private TextView id;
    private EditText nik;
    private EditText nama;
    private Spinner kelamin;
    private EditText ayah;
    private EditText ibu;
    private EditText alamat;
    private EditText lahir;
    private Button setting;
    private EditText anamnesis;
    private TumbangDbHelper mDbHelper;
    private String jenKel, idData;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDbHelper = new TumbangDbHelper(this);
        deklarasi();

        Button simpanBt = (Button)findViewById(R.id.simpan);
        simpanBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiIsi();
            }
        });
    }

    /*link xml file

     */
    private void deklarasi(){
        /* get id by date

         */
        Date dataId = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyMMddkkmmss");
        idData = df.format(dataId);

        id = (TextView)findViewById(R.id.id);
        nik = (EditText)findViewById(R.id.nik);
        nama = (EditText)findViewById(R.id.nama);
        nama.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        kelamin = (Spinner)findViewById(R.id.kelamin);
        ayah = (EditText)findViewById(R.id.ayah);
        ayah.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        ibu = (EditText)findViewById(R.id.ibu);
        ibu.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        alamat = (EditText)findViewById(R.id.alamat);
        alamat.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        lahir = (EditText)findViewById(R.id.lahir);
        setting = (Button)findViewById(R.id.setting);
        anamnesis = (EditText)findViewById(R.id.anamnesis);
        anamnesis.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        id.setText(idData);

        /*setting listener spinner kelamin

         */
        kelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)){
                    if (selection.equals("Laki-laki")){
                        jenKel="1";
                    }else {
                        jenKel="0";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                jenKel="1";
            }
        });

        /*setting date

         */
        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd-MM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                lahir.setText(sdf.format(myCalendar.getTime()));
            }

        };

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DetailDataActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    /*cek edittext sudah terisi/belum

     */
    private void validasiIsi() {
        if (nik.getText().toString().trim().length() == 0) {
            nik.setError("Harus Diisi");
        } else if (nama.getText().toString().trim().length() == 0) {
            nama.setError("Harus Diisi");
        } else if (ayah.getText().toString().trim().length() == 0) {
            ayah.setError("Harus Diisi");
        } else if (ibu.getText().toString().trim().length() == 0) {
            ibu.setError("Harus Diisi");
        } else if (alamat.getText().toString().trim().length() == 0) {
            alamat.setError("Harus Diisi");
        } else if (lahir.getText().toString().trim().length() == 0) {
            lahir.setError("Harus Diisi");
        } else {
            insertData();
        }
    }

    /*insert data into db

     */
    private void insertData(){
        String dataId = String.valueOf(idData);
        String nikString = nik.getText().toString().trim();
        String namaString = nama.getText().toString().trim();
        String ayahString = ayah.getText().toString().trim();
        String ibuString = ibu.getText().toString().trim();
        String alamatString = alamat.getText().toString().trim();
        String lahirString = lahir.getText().toString().trim();
        String anamnesisString = anamnesis.getText().toString().trim();

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dataAnak.DATA_ID, dataId);
        values.put(dataAnak.COLOUMN_NIK, nikString);
        values.put(dataAnak.COLOUMN_NAME, namaString);
        values.put(dataAnak.COLOUMN_KELAMIN, jenKel);
        values.put(dataAnak.COLOUMN_AYAH, ayahString);
        values.put(dataAnak.COLOUMN_IBU, ibuString);
        values.put(dataAnak.COLOUMN_ALAMAT, alamatString);
        values.put(dataAnak.COLOUMN_TANGGAL_LAHIR, lahirString);
        values.put(dataAnak.COLOUMN_ANAMNESIS, anamnesisString);

        long newRowId = db.insert(dataAnak.TABLE__DATA, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DetailDataActivity.this, DetailDataActivity.class);
            startActivity(intent);
            finish();
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
            Intent intent = new Intent(DetailDataActivity.this, DataActivity.class);
            String input = "";
            intent.putExtra("inp",input);
            ActivityCompat.finishAffinity(DetailDataActivity.this);
            startActivity(intent);
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
