package com.example.asyrofiabdusani.tumbangapp.Laporan;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MenuLaporanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_laporan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LinearLayout laporanPertumbuhan = (LinearLayout)findViewById(R.id.pertumbuhan);
        laporanPertumbuhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuLaporanActivity.this, LaporanPertumbuhanList.class);
                String input = "";
                intent.putExtra("inp",input);
                startActivity(intent);
            }
        });
        LinearLayout laporanPerkembangan = (LinearLayout)findViewById(R.id.perkembangan);
        laporanPerkembangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuLaporanActivity.this, LaporanPerkembanganListAnak.class);
                String input = "";
                intent.putExtra("inp",input);
                startActivity(intent);
            }
        });

        LinearLayout laporanAnak = (LinearLayout)findViewById(R.id.anak);
        laporanAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuLaporanActivity.this, LaporanAnakActivity.class);
                String input = "";
                intent.putExtra("inp",input);
                startActivity(intent);
            }
        });

        LinearLayout simpanLaporan = (LinearLayout)findViewById(R.id.simpan_laporan);
        simpanLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    if (ActivityCompat.checkSelfPermission(MenuLaporanActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED);
                    else
                        ActivityCompat.requestPermissions(MenuLaporanActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                Date dataId = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy_kkmm");
                final String tgl = df.format(dataId);

                AlertDialog.Builder builder = new AlertDialog.Builder(MenuLaporanActivity.this);
                builder.setTitle("Apakah Anda Yakin Akan Menyimpan Laporan");
                builder.setCancelable(false);

                builder.setPositiveButton(
                        "Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Laporan Tumbuh Kembang/"+tgl+"/";
                                File file = new File(directory_path);
                                if (!file.exists()) {
                                    file.mkdirs();
                                }

                                SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), TumbangDbHelper.DATABASE_NAME, directory_path);
                                sqliteToExcel.exportAllTables("Laporan.xls", new SQLiteToExcel.ExportListener() {
                                    @Override
                                    public void onStart() {

                                    }

                                    @Override
                                    public void onCompleted(String filePath) {
                                        Toast.makeText(MenuLaporanActivity.this, "Berhasil Menyimpan Laporan", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onError(Exception e) {

                                    }
                                });
                                String folder = Environment.getExternalStorageDirectory().getPath()+"/Laporan Tumbuh Kembang/";
                                Uri uri = Uri.parse(folder);
                                Intent i = new Intent();
                                i.setAction(Intent.ACTION_GET_CONTENT);
                                i.setDataAndType(uri,"*/*");
                                startActivity(Intent.createChooser(i,"open"));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
