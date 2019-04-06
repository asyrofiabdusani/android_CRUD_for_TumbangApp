package com.example.asyrofiabdusani.tumbangapp.Stimulasi;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.MainActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

import java.util.ArrayList;

public class GerakHalusActivity extends AppCompatActivity {
    private StimulusAdapter mAdapter;
    private ArrayList <ListStimulus> list = new ArrayList<ListStimulus>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerak_halus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listItem();
        listView = (ListView) findViewById(R.id.list_stimulasi);
        mAdapter= new StimulusAdapter(this,list);
        listView.setAdapter(mAdapter);
    }
    private void listItem() {
        list.add(new ListStimulus("9 - 12","Stimulasi Umur 9 - 12 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Halus.Halus9"));
        list.add(new ListStimulus("12 - 15","Stimulasi Umur 12 - 15 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Halus.Halus12"));
        list.add(new ListStimulus("15 - 18","Stimulasi Umur 15 - 18 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Halus.Halus15"));
        list.add(new ListStimulus("18 - 24","Stimulasi Umur 18 - 24 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Halus.Halus18"));
        list.add(new ListStimulus("24 - 36","Stimulasi Umur 24 - 36 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Halus.Halus24"));
        list.add(new ListStimulus("36 - 48","Stimulasi Umur 36 - 48 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Halus.Halus36"));
        list.add(new ListStimulus("48 - 60","Stimulasi Umur 48 - 60 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Halus.Halus48"));
        list.add(new ListStimulus("60 - 72","Stimulasi Umur 60 - 72 Bulan",
                "com.example.asyrofiabdusani.tumbangapp.Stimulasi.Halus.Halus60"));
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


/*
class list stimulus
 */
class ListStimulus{
    private String mUmur;
    private String mMenu;
    private String mActivtiy;

    public ListStimulus(String defUmur, String defMenu, String defAct){
        mUmur = defUmur;
        mMenu = defMenu;
        mActivtiy = defAct;
    }
    public String getmUmur(){
        return mUmur;
    }
    public String getmMenu(){
        return mMenu;
    }
    public String getmActivtiy(){
        return mActivtiy;
    }
}

/*
class stimulusAdapter
 */
class StimulusAdapter extends ArrayAdapter<ListStimulus>{

    public StimulusAdapter(@NonNull Context context, ArrayList<ListStimulus> list) {
        super(context, 0,list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.stimulasi_simple_list, parent, false);
        }
        final ListStimulus list = (ListStimulus) getItem(position);

        TextView umurText = (TextView)listItemView.findViewById(R.id.rectangle);
        umurText.setText(list.getmUmur());

        TextView menuText = (TextView)listItemView.findViewById(R.id.menu);
        menuText.setText(list.getmMenu());

        LinearLayout listLinear = (LinearLayout) listItemView.findViewById(R.id.list_umur);
        listLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Class cls = Class.forName(list.getmActivtiy());
                    Intent intent = new Intent(v.getContext(), cls);
                    v.getContext().startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        });

        return listItemView;
    }

}