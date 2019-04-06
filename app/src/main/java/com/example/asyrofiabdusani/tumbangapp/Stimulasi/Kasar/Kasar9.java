package com.example.asyrofiabdusani.tumbangapp.Stimulasi.Kasar;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class Kasar9 extends AppCompatActivity {
    private StimulasiListAdapter mAdapter;
    private ArrayList<StimulasiList> list = new ArrayList<StimulasiList>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerak_halus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listItem();
        listView = (ListView) findViewById(R.id.list_stimulasi);
        mAdapter= new StimulasiListAdapter(this,list);
        listView.setAdapter(mAdapter);
    }
    private void listItem() {
        list.add(new StimulasiList("Merangkak","",
                getString(R.string.merangkak)));
        list.add(new StimulasiList("Berdiri","",
                getString(R.string.berdiri)));
        list.add(new StimulasiList("Berjalan Sambil Berpegangan",
                "",getString(R.string.berjalan_sambil_berpegangan)));
        list.add(new StimulasiList("Berjalan Dengan Bantuan","",
                getString(R.string.berjalan_dengan_bantuan)));
        list.add(new StimulasiList("Bermain Bola","",
                getString(R.string.bermain_bola)));
        list.add(new StimulasiList("Membungkuk",
                "",getString(R.string.memnbungkuk)));
        list.add(new StimulasiList("Berjalan Sendiri","",
                getString(R.string.berjalan_sendiri)));
        list.add(new StimulasiList("Naik Tangga","",
                getString(R.string.naik_tangga)));

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

class StimulasiList{
    private String mMain;
    private String mMain2;
    private String mSub;
    private int mImage;
    private final static int NO_IMAGE = -1;

    public StimulasiList(String defMain, String defMain2, String defSub, int defImage) {
        mMain = defMain;
        mMain2 = defMain2;
        mSub = defSub;
        mImage = defImage;
    }

    public StimulasiList(String defMain, String defMain2, String defSub) {
        mMain = defMain;
        mMain2 = defMain2;
        mSub = defSub;
    }
    public StimulasiList(String defSub) {
        mSub = defSub;
    }

    public String getmMain(){
        return mMain;
    }

    public String getmMain2(){ return mMain2; }

    public String getmSub(){
        return mSub;
    }

    public int getmImage() {
        return mImage;
    }

    public boolean hasImage() {
        return mImage != NO_IMAGE;
    }
}

class StimulasiListAdapter extends ArrayAdapter<StimulasiList> {

    public StimulasiListAdapter(@NonNull Context context, ArrayList<StimulasiList>list) {
        super(context, 0, list);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.stimulasi_list, parent, false);
        }
        StimulasiList list= (StimulasiList) getItem(position);

        TextView mainText = (TextView) listItemView.findViewById(R.id.main_text);
        if (list.getmMain().equals("")){
            mainText.setVisibility(View.GONE);
        }else {
            mainText.setText(list.getmMain());
            mainText.setVisibility(View.VISIBLE);
        }

        TextView mainText2 = (TextView) listItemView.findViewById(R.id.main2_text);
        if (list.getmMain2().equals("")){
            mainText2.setVisibility(View.GONE);
        }else {
            mainText2.setText(list.getmMain2());
            mainText2.setVisibility(View.VISIBLE);
        }

        TextView subText = (TextView) listItemView.findViewById(R.id.sub_text);
        subText.setText(list.getmSub());

        ImageView imageView=(ImageView) listItemView.findViewById(R.id.stimulasi_pic);
        if (list.hasImage()){
            imageView.setImageResource(list.getmImage());
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setVisibility(View.GONE);
        }
        return listItemView;
    }
}

