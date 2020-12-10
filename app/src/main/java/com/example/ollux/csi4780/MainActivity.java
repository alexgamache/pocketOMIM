package com.example.ollux.csi4780;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private EditText et;
    public CustomAdapter adapter;
    public Integer textlength;
    public List<ItemsModel> disorder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.disorders);
        et = (EditText) findViewById(R.id.disorderSearch);

        String json;
        try {
            InputStream is = getAssets().open("test.json");
            StringWriter writer = new StringWriter();
            String reader = CharStreams.toString(new InputStreamReader(
                    is, StandardCharsets.UTF_8));

            final JSONArray jsonArray = new JSONArray(reader);

            for (int i = 0; i < jsonArray.length(); i++) {
                final JSONObject c = jsonArray.getJSONObject(i);

                String title = c.getString("Title");
                String entry = c.getString("Entry");
                String aka = c.getString("AKA");
                JSONArray a = c.getJSONArray("Contents");
                String a2 = a.toString();
                String a3 = a2.substring( 1, a2.length() - 1 );
                ItemsModel itemsModel = new ItemsModel(title, entry, aka, a3);
                disorder.add(itemsModel);
            }

            adapter = new CustomAdapter(
                    MainActivity.this, disorder);

            lv.setAdapter(adapter);

            et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    adapter.getFilter().filter(charSequence);

                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}

