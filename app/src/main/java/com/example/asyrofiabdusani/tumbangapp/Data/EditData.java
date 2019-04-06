package com.example.asyrofiabdusani.tumbangapp.Data;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract.dataAnak;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditData extends AppCompatActivity {
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
    private String jenKel, getId;
    private Calendar myCalendar;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        deklarasi();

        Button simpanBt = (Button)findViewById(R.id.simpan);
        simpanBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiIsi();
            }
        });
        Button hapus = (Button) findViewById(R.id.hapus);

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditData.this);
                builder.setTitle("Apakah Anda Yakin Akan Menghapus Data");
                builder.setCancelable(false);

                builder.setPositiveButton(
                        "Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hapusData();
                                Intent intent = new Intent(EditData.this, DataActivity.class);
                                String input = "";
                                intent.putExtra("inp",input);
                                startActivity(intent);
                                Toast.makeText(EditData.this,"Data berhasil dihapus",Toast.LENGTH_LONG).show();
                                finish();
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
        });
    }

    private void deklarasi(){
        id = (TextView)findViewById(R.id.id);
        nik = (EditText)findViewById(R.id.nik);
        nik.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
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
        lahir.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        setting = (Button)findViewById(R.id.setting);
        anamnesis = (EditText)findViewById(R.id.anamnesis);
        anamnesis.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        mDbHelper = new TumbangDbHelper(this);
        bacaDb();
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
                new DatePickerDialog(EditData.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void bacaDb(){
        Bundle extra = getIntent().getExtras();
        getId = extra.getString("id");

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM " +dataAnak.TABLE__DATA+ " WHERE "
                        + dataAnak.DATA_ID + " = ?",
                new String[]{getId});
        try {
            int idDb = cursor.getColumnIndex(dataAnak.DATA_ID);
            int nikDb = cursor.getColumnIndex(dataAnak.COLOUMN_NIK);
            int namaDb = cursor.getColumnIndex(dataAnak.COLOUMN_NAME);
            int kelaminDb = cursor.getColumnIndex(dataAnak.COLOUMN_KELAMIN);
            int ayahDb = cursor.getColumnIndex(dataAnak.COLOUMN_AYAH);
            int ibuDb = cursor.getColumnIndex(dataAnak.COLOUMN_IBU);
            int lahirDb = cursor.getColumnIndex(dataAnak.COLOUMN_TANGGAL_LAHIR);
            int alamatDb = cursor.getColumnIndex(dataAnak.COLOUMN_ALAMAT);
            int anamnesisDb = cursor.getColumnIndex(dataAnak.COLOUMN_ANAMNESIS);

            while (cursor.moveToNext()) {
                String currentId = cursor.getString(idDb);
                String currentNik = cursor.getString(nikDb);
                String currentNama = cursor.getString(namaDb);
                String currentKel = cursor.getString(kelaminDb);
                String currentAyah = cursor.getString(ayahDb);
                String currentIbu = cursor.getString(ibuDb);
                String currentLahir = cursor.getString(lahirDb);
                String currentAlamat = cursor.getString(alamatDb);
                String currentAnamnesis = cursor.getString(anamnesisDb);

                id.setText(currentId);
                nik.setText(currentNik);
                nama.setText(currentNama);
                ayah.setText(currentAyah);
                ibu.setText(currentIbu);
                lahir.setText(currentLahir);
                alamat.setText(currentAlamat);
                anamnesis.setText(currentAnamnesis);
                if (currentKel.equals("1")){
                    kelamin.setSelection(0);
                }else {
                    kelamin.setSelection(1);
                }
            }
        } finally {
            cursor.close();
        }
    }

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
            AlertDialog.Builder builder = new AlertDialog.Builder(EditData.this);
            builder.setTitle("Apakah Anda Yakin Akan Mengubah Data");
            builder.setCancelable(false);

            builder.setPositiveButton(
                    "Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            insertData();
                            Intent intent = new Intent(EditData.this, DataActivity.class);
                            String input = "";
                            intent.putExtra("inp",input);
                            startActivity(intent);
                            Toast.makeText(EditData.this,"Data berhasil dirubah",Toast.LENGTH_LONG).show();
                            finish();
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
        String nikString = nik.getText().toString().trim();
        String namaString = nama.getText().toString().trim();
        String ayahString = ayah.getText().toString().trim();
        String ibuString = ibu.getText().toString().trim();
        String alamatString = alamat.getText().toString().trim();
        String lahirString = lahir.getText().toString().trim();
        String anamnesisString = anamnesis.getText().toString().trim();

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dataAnak.COLOUMN_NIK, nikString);
        values.put(dataAnak.COLOUMN_NAME, namaString);
        values.put(dataAnak.COLOUMN_KELAMIN, jenKel);
        values.put(dataAnak.COLOUMN_AYAH, ayahString);
        values.put(dataAnak.COLOUMN_IBU, ibuString);
        values.put(dataAnak.COLOUMN_ALAMAT, alamatString);
        values.put(dataAnak.COLOUMN_TANGGAL_LAHIR, lahirString);
        values.put(dataAnak.COLOUMN_ANAMNESIS, anamnesisString);

        db.update(dataAnak.TABLE__DATA,  values, dataAnak.DATA_ID + " = ?", new String[]{getId});

    }

    private void hapusData(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(dataAnak.TABLE__DATA, dataAnak.DATA_ID + " = ?",  new String[]{getId});
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
