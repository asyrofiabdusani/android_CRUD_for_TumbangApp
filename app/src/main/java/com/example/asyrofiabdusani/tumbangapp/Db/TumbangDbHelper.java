package com.example.asyrofiabdusani.tumbangapp.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract.dataAnak;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract.perkembangan;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract.pertumbuhan;

/**
 * Created by Asyrofi Abdusani on 16/04/2018.
 */

public class TumbangDbHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "tumbang.db";

    private static final int DATABASE_VERSION = 1;

    public TumbangDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE_DATA =  "CREATE TABLE " + dataAnak.TABLE__DATA + " ("
                + dataAnak.DATA_ID + " INTEGER PRIMARY KEY NOT NULL, "
                + dataAnak.COLOUMN_NIK + " TEXT NOT NULL, "
                + dataAnak.COLOUMN_NAME + " TEXT NOT NULL, "
                + dataAnak.COLOUMN_KELAMIN + " TEXT NOT NULL, "
                + dataAnak.COLOUMN_AYAH + " TEXT NOT NULL, "
                + dataAnak.COLOUMN_IBU + " TEXT NOT NULL, "
                + dataAnak.COLOUMN_ALAMAT + " TEXT NOT NULL, "
                + dataAnak.COLOUMN_TANGGAL_LAHIR + " TEXT NOT NULL, "
                + dataAnak.COLOUMN_ANAMNESIS + " TEXT);";

        String SQL_CREATE_TABLE_PERTUMBUHAN =  "CREATE TABLE " + pertumbuhan.TABLE_PERTUMBUHAN + " ("
                + pertumbuhan.PERTUMBUHAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + pertumbuhan.COLOUMN_TANGGAL_PERTUMBUHAN + " TEXT NOT NULL, "
                + pertumbuhan.COLOUMN_ID_DATA + " TEXT NOT NULL, "
                + pertumbuhan.COLOUMN_USIA_PERTUMBUHAN + " TEXT NOT NULL, "
                + pertumbuhan.COLOUMN_BERAT_BADAN + " TEXT NOT NULL, "
                + pertumbuhan.COLOUMN_TINGGI_BADAN + " TEXT NOT NULL, "
                + pertumbuhan.COLOUMN_LINGKAR_KEPALA + " TEXT, "
                + pertumbuhan.COLOUMN_IMT  + " TEXT NOT NULL, "
                + pertumbuhan.COLOUMN_HASIL_TB + " TEXT NOT NULL, "
                + pertumbuhan.COLOUMN_HASIL_BB + " TEXT NOT NULL, "
                + pertumbuhan.COLOUMN_HASIL_LKA + " TEXT NOT NULL, "
                + pertumbuhan.COLOUMN_HASIL_IMT + " TEXT);";

        String SQL_CREATE_TABLE_PERKEMBANGAN =  "CREATE TABLE " + perkembangan.TABLE_PERKEMBANGAN + " ("
                + perkembangan.PERKEMBANGAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + perkembangan.COLOUMN_TANGGAL_PERKEMBANGAN + " TEXT NOT NULL, "
                + perkembangan.COLOUMN_ID_DATA + " TEXT NOT NULL, "
                + perkembangan.COLOUMN_USIA_PERKEMBANGAN + " TEXT NOT NULL, "
                + perkembangan.COLOUMN_PERKEMBANGAN_IDENTIFIKASI + " TEXT NOT NULL, "
                + perkembangan.COLOUMN_GANGGUAN_1 + " TEXT, "
                + perkembangan.COLOUMN_GANGGUAN_2 + " TEXT, "
                + perkembangan.COLOUMN_GANGGUAN_3 + " TEXT, "
                + perkembangan.COLOUMN_GANGGUAN_4 + " TEXT, "
                + perkembangan.COLOUMN_GANGGUAN_5 + " TEXT);";

        db.execSQL(SQL_CREATE_TABLE_DATA);
        db.execSQL(SQL_CREATE_TABLE_PERTUMBUHAN);
        db.execSQL(SQL_CREATE_TABLE_PERKEMBANGAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
