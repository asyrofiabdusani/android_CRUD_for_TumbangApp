package com.example.asyrofiabdusani.tumbangapp.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.asyrofiabdusani.tumbangapp.Perkembangan.PerkembanganActivity;
import com.example.asyrofiabdusani.tumbangapp.Pertumbuhan.PertumbuhanActivity;
import com.example.asyrofiabdusani.tumbangapp.R;

public class SearchPerkembanganActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final EditText searchText = (EditText)findViewById(R.id.search_text);
        searchText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        ImageView searchBt = (ImageView)findViewById(R.id.search_setting);

        searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = searchText.getText().toString().trim();
                Intent intent = new Intent(SearchPerkembanganActivity.this, PerkembanganActivity.class);
                intent.putExtra("input",nama);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
