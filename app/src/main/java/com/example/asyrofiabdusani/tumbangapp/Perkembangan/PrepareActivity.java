package com.example.asyrofiabdusani.tumbangapp.Perkembangan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.Pertumbuhan.HitungPertumbuhan;
import com.example.asyrofiabdusani.tumbangapp.R;

public class PrepareActivity extends AppCompatActivity {
    private TextView jawaban1;
    private TextView jawaban2;
    private TextView jawaban3;
    private TextView jawaban4;
    private TextView jawaban5;
    private TextView jawaban6;
    private TextView jawaban7;
    private TextView jawaban8;
    private TextView jawaban9;
    private TextView jawaban10;
    private TextView no;
    private Button proses;
    private String currentId;
    private String kategori [] = new String[10];
    private int usia;
    private int ceklis[] = new int[10];
    private String nilaiCek[] = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getVal();
        deklarasi();

        proses = (Button)findViewById(R.id.proses);
        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PrepareActivity.this);
                builder.setTitle("Apakah Anda Yakin Akan Memproses Data");
                builder.setCancelable(false);

                builder.setPositiveButton(
                        "Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent nextIntent = new Intent(PrepareActivity.this,DiagnosisKpspActivity.class);
                                nextIntent.putExtra("id", currentId);
                                nextIntent.putExtra("umur", usia);
                                nextIntent.putExtra("ceklis", ceklis);
                                nextIntent.putExtra("kategori",kategori);
                                startActivity(nextIntent);
                                ActivityCompat.finishAffinity(PrepareActivity.this);
                                nextIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
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

    private void getVal(){
        Bundle extra = getIntent().getExtras();
        currentId = extra.getString("id");
        usia = extra.getInt("umur");
        ceklis = extra.getIntArray("ceklis");
        kategori = extra.getStringArray("kategori");
    }

    private void deklarasi() {
        jawaban1 = findViewById(R.id.jawaban1);
        jawaban2 = findViewById(R.id.jawaban2);
        jawaban3 = findViewById(R.id.jawaban3);
        jawaban4 = findViewById(R.id.jawaban4);
        jawaban5 = findViewById(R.id.jawaban5);
        jawaban6 = findViewById(R.id.jawaban6);
        jawaban7 = findViewById(R.id.jawaban7);
        jawaban8 = findViewById(R.id.jawaban8);
        jawaban9 = findViewById(R.id.jawaban9);
        jawaban10 = findViewById(R.id.jawaban10);
        no = findViewById(R.id.no10);
        for (int i = 0; i < 10; i++) {
            if (ceklis[i] == 0) {
                nilaiCek[i] = "Tidak";
            } else {
                nilaiCek[i] = "Ya";
            }
        }

        jawaban1.setText(String.valueOf(nilaiCek [0]));
        jawaban2.setText(String.valueOf(nilaiCek [1]));
        jawaban3.setText(String.valueOf(nilaiCek [2]));
        jawaban4.setText(String.valueOf(nilaiCek [3]));
        jawaban5.setText(String.valueOf(nilaiCek [4]));
        jawaban6.setText(String.valueOf(nilaiCek [5]));
        jawaban7.setText(String.valueOf(nilaiCek [6]));
        jawaban8.setText(String.valueOf(nilaiCek [7]));
        jawaban9.setText(String.valueOf(nilaiCek [8]));
        if ((usia >= 42 && usia <= 53)) {
            jawaban10.setVisibility(View.GONE);
            no.setVisibility(View.GONE);
        }else {
            jawaban10.setText(String.valueOf(nilaiCek[9]));
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
            AlertDialog.Builder builder = new AlertDialog.Builder(PrepareActivity.this);
            builder.setTitle("Apakah Anda Yakin Akan Membatalkan Tes");
            builder.setCancelable(false);

            builder.setPositiveButton(
                    "Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(PrepareActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                            ActivityCompat.finishAffinity(PrepareActivity.this);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
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
        return super.onOptionsItemSelected(item);
    }
}
