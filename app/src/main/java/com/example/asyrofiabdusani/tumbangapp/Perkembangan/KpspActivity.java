package com.example.asyrofiabdusani.tumbangapp.Perkembangan;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class KpspActivity extends AppCompatActivity {
    private String currentId;
    private String kategori[] = new String[1];
    private int usia;
    private int check[] = new int[1];
    private KpspListAdapter mAdapter;
    private ArrayList<KpspList> list = new ArrayList<KpspList>();
    private ListView listView;

    public KpspActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kpsp_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getVal();
        listItem();
        listView = (ListView) findViewById(R.id.list_stimulasi);
        mAdapter= new KpspListAdapter(this,list);
        listView.setAdapter(mAdapter);

        Button nextButton = (Button)findViewById(R.id.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCheckBox();
                Intent nextIntent = new Intent(KpspActivity.this,Kpsp2Activity.class);
                nextIntent.putExtra("id", currentId);
                nextIntent.putExtra("umur", usia);
                nextIntent.putExtra("ceklis", check);
                nextIntent.putExtra("kategori",kategori);
                startActivity(nextIntent);
            }
        });
    }

    private void getVal(){
        Bundle extra = getIntent().getExtras();
        currentId = extra.getString("id");
        usia = extra.getInt("umur");
    }

    private void getCheckBox () {
        KpspList listPertanyaan = (KpspList) mAdapter.getItem(0);
        check [0]= listPertanyaan.getmJawaban();
        kategori[0] = listPertanyaan.getmKategori();
    }

    private void listItem() {
        if (usia>=11 && usia<=14){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.bersembunyi),"Sosialisasi dan Kemandirian"));
        }
        if (usia>=15 && usia<=17){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.mempertemukanKubus),"Gerak Halus"));
        }
        if (usia>=18 && usia<=20){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.bertepukTangan),"Sosialisasi dan Kemandirian"));
        }
        if (usia>=21 && usia<=23){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.membungkuk),"Gerak Kasar"));
        }
        if (usia>=24 && usia<=29){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.meniruPekerjaanRumah),"Sosialisasi dan Kemandirian"));
        }
        if (usia>=30 && usia<=35){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.melepasPakaian),"Sosialisasi dan Kemandirian"));
        }
        if (usia>=36 && usia<=41){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.diberiPensil),"Gerak Halus"));
        }
        if (usia>=42 && usia<=47){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.mengenakanSepatu),"Sosialisasi dan Kemandirian"));
        }
        if (usia>=48 && usia<=53){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.mengayuhSepeda),"Gerak Kasar"));
        }
        if (usia>=54 && usia<=59){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.meletakkan8Kubus),"Gerak Halus"));
        }
        if (usia>=60 && usia<=65){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.isiTitikTitik),"Bahasa dan Bicara"));
        }
        if (usia>=66 && usia<=71){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.gambarPlus), R.drawable.gbrplus, "Gerak Halus"));
        }
        if (usia==72){
            list.add(new KpspList("Pertanyaan 1", getString(R.string.pilihanWarna), R.drawable.gbrwarna, "Bahasa dan Bicara"));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

class KpspList{
    private String mJudul;
    private String mPertanyaan;
    private int mImage;
    private final static int NO_IMAGE = -1;
    public int mJawaban;
    public String mKategori;

    public KpspList(String defJudul, String defPertanyaan, int defImage, String defKategori) {
        mJudul = defJudul;
        mPertanyaan = defPertanyaan;
        mImage = defImage;
        mKategori = defKategori;
    }

    public KpspList(String defJudul, String defPertanyaan, String defKategori) {
        mJudul = defJudul;
        mPertanyaan = defPertanyaan;
        mKategori = defKategori;
    }
    public String getmJudul(){
        return mJudul;
    }

    public String getmPertanyaan(){ return mPertanyaan; }

    public int getmImage() {
        return mImage;
    }

    public boolean hasImage() {
        return mImage != NO_IMAGE;
    }

    public int getmJawaban(){
        return mJawaban;
    }

    public String getmKategori(){ return mKategori; }

}

class KpspListAdapter extends ArrayAdapter<KpspList> {

    public KpspListAdapter(@NonNull Context context, ArrayList<KpspList>list) {
        super(context, 0, list);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_kpsp, parent, false);
        }
        final KpspList list= (KpspList) getItem(position);

        TextView judulText = (TextView) listItemView.findViewById(R.id.judul_text);
        judulText.setText(list.getmJudul());

        TextView pertanyaanText = (TextView) listItemView.findViewById(R.id.pertanyaan_text);
        pertanyaanText.setText(list.getmPertanyaan());

        ImageView imageView=(ImageView) listItemView.findViewById(R.id.stimulasi_pic);
        if (list.hasImage()){
            imageView.setImageResource(list.getmImage());
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        CheckBox checkBox = (CheckBox) listItemView.findViewById(R.id.check);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.mJawaban = 1;
                } else if (!isChecked) {
                    list.mJawaban = 0;
                }
            }
        });
        return listItemView;
    }
}
