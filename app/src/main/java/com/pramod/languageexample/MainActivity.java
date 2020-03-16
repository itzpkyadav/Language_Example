package com.pramod.languageexample;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    Spinner mLanguage;
    Button btn_next;
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLanguage = findViewById(R.id.spLanguage);
        mTextView = findViewById(R.id.textView);
        btn_next = findViewById(R.id.btn_next);
        mAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.language_option));
        mLanguage.setAdapter(mAdapter);

        if (LocaleHelper.getLanguage(MainActivity.this).equalsIgnoreCase("en")) {
            mLanguage.setSelection(mAdapter.getPosition("English"));
        } else if (LocaleHelper.getLanguage(MainActivity.this).equalsIgnoreCase("hi")) {
            mLanguage.setSelection(mAdapter.getPosition("Hindi"));
        } else if (LocaleHelper.getLanguage(MainActivity.this).equalsIgnoreCase("kn")) {
            mLanguage.setSelection(mAdapter.getPosition("Kannada"));
        }

        mLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Context context;
                Resources resources;
                switch (i) {
                    case 0:
                        context = LocaleHelper.setLocale(MainActivity.this, "en");
                        resources = context.getResources();
                        mTextView.setText(resources.getString(R.string.text_translation));
                        break;
                    case 1:
                        context = LocaleHelper.setLocale(MainActivity.this, "hi");
                        resources = context.getResources();
                        mTextView.setText(resources.getString(R.string.text_translation));
                        break;
                    case 2:
                        context = LocaleHelper.setLocale(MainActivity.this, "kn");
                        resources = context.getResources();
                        mTextView.setText(resources.getString(R.string.text_translation));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Dashboard.class));
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
}
