package com.example.asyrofiabdusani.tumbangapp.Db;

import android.provider.BaseColumns;

/**
 * Created by Asyrofi Abdusani on 16/04/2018.
 */

public class TumbangContract {
    private TumbangContract() {}

    public static final class dataAnak implements BaseColumns {
        public final static String TABLE__DATA = "data_anak";

        public final static String DATA_ID = "id_anak";
        public final static String COLOUMN_NIK = "nik";
        public final static String COLOUMN_NAME = "nama";
        public final static String COLOUMN_KELAMIN = "kelamin";
        public final static String COLOUMN_AYAH = "ayah";
        public final static String COLOUMN_IBU = "ibu";
        public final static String COLOUMN_ALAMAT = "alamat";
        public final static String COLOUMN_TANGGAL_LAHIR = "lahir";
        public final static String COLOUMN_ANAMNESIS = "anamnesis";
    }

    public static final class pertumbuhan implements BaseColumns {
        public final static String TABLE_PERTUMBUHAN = "pertumbuhan";

        public final static String PERTUMBUHAN_ID = BaseColumns._ID;
        public final static String COLOUMN_TANGGAL_PERTUMBUHAN = "tanggal";
        public final static String COLOUMN_ID_DATA = "id_anak";
        public final static String COLOUMN_USIA_PERTUMBUHAN = "usia_anak";
        public final static String COLOUMN_BERAT_BADAN = "bb";
        public final static String COLOUMN_TINGGI_BADAN = "tb";
        public final static String COLOUMN_LINGKAR_KEPALA = "lk";
        public final static String COLOUMN_IMT = "imt";
        public final static String COLOUMN_HASIL_BB = "kesimpulan_bb";
        public final static String COLOUMN_HASIL_TB = "kesimpulan_tb";
        public final static String COLOUMN_HASIL_LKA = "kesimpulan_lka";
        public final static String COLOUMN_HASIL_IMT= "kesimpulan_imt";
    }
    public static final class perkembangan implements BaseColumns {
        public final static String TABLE_PERKEMBANGAN = "perkembangan";

        public final static String PERKEMBANGAN_ID = BaseColumns._ID;
        public final static String COLOUMN_TANGGAL_PERKEMBANGAN = "tanggal";
        public final static String COLOUMN_ID_DATA = "id_anak";
        public final static String COLOUMN_USIA_PERKEMBANGAN = "usia_anak";
        public final static String COLOUMN_PERKEMBANGAN_IDENTIFIKASI = "identifikasi";
        public final static String COLOUMN_GANGGUAN_1 = "gangguan_1";
        public final static String COLOUMN_GANGGUAN_2 = "gangguan_2";
        public final static String COLOUMN_GANGGUAN_3 = "gangguan_3";
        public final static String COLOUMN_GANGGUAN_4 = "gangguan_4";
        public final static String COLOUMN_GANGGUAN_5 = "gangguan_5";
    }
}
