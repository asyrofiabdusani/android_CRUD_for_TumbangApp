package com.example.asyrofiabdusani.tumbangapp.Stimulasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.asyrofiabdusani.tumbangapp.R;

public class StimulasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stimulasi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*
        into bahasa act
         */
        LinearLayout bahasaLn = (LinearLayout)findViewById(R.id.bahasa);
        bahasaLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StimulasiActivity.this, BahasaActivity.class);
                startActivity(intent);
            }
        });

        /*
        into gerak halus act
         */
        LinearLayout halusLn = (LinearLayout)findViewById(R.id.halus);
        halusLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StimulasiActivity.this, GerakHalusActivity.class);
                startActivity(intent);
            }
        });

        /*
        into gerak kasar act
         */
        LinearLayout kasarLn = (LinearLayout)findViewById(R.id.kasar);
        kasarLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StimulasiActivity.this, GerakKasarActivity.class);
                startActivity(intent);
            }
        });

        /*
        into sosialisasi act
         */
        LinearLayout sosialLn = (LinearLayout)findViewById(R.id.sosial);
        sosialLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StimulasiActivity.this, SosialisasiActivity.class);
                startActivity(intent);
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
