package com.example.asyrofiabdusani.tumbangapp.Pertumbuhan;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asyrofiabdusani.tumbangapp.Db.TumbangContract;
import com.example.asyrofiabdusani.tumbangapp.Db.TumbangDbHelper;
import com.example.asyrofiabdusani.tumbangapp.R;
import com.example.asyrofiabdusani.tumbangapp.Search.SearchPertumbuhanActivity;

import java.util.ArrayList;

public class PertumbuhanActivity extends AppCompatActivity {
    private TumbangDbHelper mDbHelper;
    private Cursor cursor;
    private DataAdapter mAdapter;
    private ArrayList<DataList> list = new ArrayList<DataList>();
    private ListView listView;
    private EditText searchEdit;
    private String getInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_anak);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getValue();
        searchEdit = (EditText)findViewById(R.id.search);
        searchEdit.setText(getInput);
        String searchString = searchEdit.getText().toString().trim();
        mDbHelper = new TumbangDbHelper(this);

        if (searchString.equals("")){
            dataDb();
        }else {
            DataSearch();
        }

        listView = (ListView) findViewById(R.id.data_list);
        mAdapter = new DataAdapter(this, list);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PertumbuhanActivity.this, HitungPertumbuhan.class);
                DataList pilihId = (DataList) mAdapter.getItem(position);
                intent.putExtra("id", pilihId.getmId());
                startActivity(intent);
            }
        });

        searchEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PertumbuhanActivity.this, SearchPertumbuhanActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getValue(){
        Bundle extra = getIntent().getExtras();
        getInput = extra.getString("input");
    }

    private void DataSearch(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM " + TumbangContract.dataAnak.TABLE__DATA+ " WHERE "
                        + TumbangContract.dataAnak.COLOUMN_NAME + " = ?",
                new String[]{getInput});

        try {
            int nameColoumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_NAME);
            int ibuColumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_IBU);
            int lahirColumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_TANGGAL_LAHIR);
            int idColumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.DATA_ID);
            int kelColoumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_KELAMIN);

            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColoumnIndex);
                String currentIbu = cursor.getString(ibuColumnIndex);
                String currentLahir = cursor.getString(lahirColumnIndex);
                String currentId = cursor.getString(idColumnIndex);
                String currentkel = cursor.getString(kelColoumnIndex);

                list.add(new DataList(currentName,currentIbu,currentLahir,currentId,currentkel));
            }
        } finally {
            cursor.close();
        }
    }

    private void dataDb() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                TumbangContract.dataAnak.COLOUMN_NAME,
                TumbangContract.dataAnak.COLOUMN_IBU,
                TumbangContract.dataAnak.COLOUMN_TANGGAL_LAHIR,
                TumbangContract.dataAnak.DATA_ID,
                TumbangContract.dataAnak.COLOUMN_KELAMIN
        };

        cursor = db.query(
                TumbangContract.dataAnak.TABLE__DATA,
                projection,
                null,
                null,
                null,
                null,
                TumbangContract.dataAnak.COLOUMN_NAME + " ASC");


        try {
            int nameColoumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_NAME);
            int ibuColumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_IBU);
            int lahirColumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_TANGGAL_LAHIR);
            int idColumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.DATA_ID);
            int kelColoumnIndex = cursor.getColumnIndex(TumbangContract.dataAnak.COLOUMN_KELAMIN);

            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColoumnIndex);
                String currentIbu = cursor.getString(ibuColumnIndex);
                String currentLahir = cursor.getString(lahirColumnIndex);
                String currentId = cursor.getString(idColumnIndex);
                String currentkel = cursor.getString(kelColoumnIndex);

                list.add(new DataList(currentName,currentIbu,currentLahir,currentId,currentkel));
            }
        } finally {
            cursor.close();
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



class DataList{
    private String mNama;
    private String mLahir;
    private String mIbu;
    private String mId;
    private String mKelamin;

    public DataList (String defNama, String defLahir, String defIbu, String defId, String defKelamin){
        mNama = defNama;
        mLahir = defLahir;
        mIbu = defIbu;
        mId = defId;
        mKelamin = defKelamin;
    }
    public String getmNama(){
        return mNama;
    }
    public String getmLahir(){
        return mLahir;
    }
    public String getmIbu(){
        return mIbu;
    }
    public String getmId(){
        return mId;
    }
    public String getmKelamin(){
        return mKelamin;
    }
}

class DataAdapter extends ArrayAdapter<DataList> {

    public DataAdapter(@NonNull Context context, ArrayList<DataList> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.data_simple_list, parent, false);
        }
       DataList list = (DataList) getItem(position);

        TextView nama = (TextView) listItemView.findViewById(R.id.nama);
        nama.setText(list.getmNama());

        TextView ibu = (TextView) listItemView.findViewById(R.id.ibu);
        ibu.setText(list.getmIbu());

        TextView lahir = (TextView) listItemView.findViewById(R.id.lahir);
        lahir.setText(list.getmLahir());

        ImageView img = (ImageView) listItemView.findViewById(R.id.pic);
        if (list.getmKelamin().equals("1")){
            img.setImageResource(R.drawable.anak);
        }else {
            img.setImageResource(R.drawable.cewek);
        }

        return listItemView;
    }

}
