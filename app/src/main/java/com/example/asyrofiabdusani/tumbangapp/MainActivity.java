package com.example.asyrofiabdusani.tumbangapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.asyrofiabdusani.tumbangapp.Data.DataActivity;
import com.example.asyrofiabdusani.tumbangapp.Laporan.MenuLaporanActivity;
import com.example.asyrofiabdusani.tumbangapp.Perkembangan.PerkembanganActivity;
import com.example.asyrofiabdusani.tumbangapp.Perkembangan.PrepareActivity;
import com.example.asyrofiabdusani.tumbangapp.Pertumbuhan.HitungPertumbuhan;
import com.example.asyrofiabdusani.tumbangapp.Pertumbuhan.PertumbuhanActivity;
import com.example.asyrofiabdusani.tumbangapp.Stimulasi.StimulasiActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED);
            else
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        /*
        ke data activity
         */
        LinearLayout dataLn = (LinearLayout)findViewById(R.id.data);
        dataLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                String input = "";
                intent.putExtra("inp",input);
                startActivity(intent);
            }
        });

        /*
        ke pertumbuhan activity
         */
        LinearLayout tumbuhLn = (LinearLayout)findViewById(R.id.pertumbuhan);
        tumbuhLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PertumbuhanActivity.class);
                String input = "";
                intent.putExtra("inp",input);
                startActivity(intent);
            }
        });

        /*
        ke perkembangan activity
         */
        LinearLayout kembangLn = (LinearLayout)findViewById(R.id.perkembangan);
        kembangLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PerkembanganActivity.class);
                String input = "";
                intent.putExtra("inp",input);
                startActivity(intent);
            }
        });

        /*
        ke stimulasi activity
         */
        LinearLayout stimulasiLn = (LinearLayout)findViewById(R.id.stimulasi);
        stimulasiLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StimulasiActivity.class);
                startActivity(intent);
            }
        });

        /*
        ke laporan activity
         */
        LinearLayout laporanLn = (LinearLayout)findViewById(R.id.laporan);
        laporanLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuLaporanActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.close){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Apakah Anda Yakin Akan Keluar Dari Aplikasi");

            builder.setPositiveButton(
                    "Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.finishAffinity(MainActivity.this);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
