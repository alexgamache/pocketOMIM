package com.example.ollux.csi4780;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.JsonReader;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.common.io.CharStreams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private EditText et;
    public SimpleAdapter adapter;
    ArrayList<HashMap<String, String>> disorderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        disorderList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.disorders);
        et = (EditText) findViewById(R.id.disorderSearch);

        String json;
        try {
            InputStream is = getAssets().open("test.json");
            StringWriter writer = new StringWriter();
            String reader = CharStreams.toString(new InputStreamReader(
                    is, StandardCharsets.UTF_8));

            System.out.println(reader);
            JSONArray jsonArray = new JSONArray(reader);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);

                String title = c.getString("Title");
                String entry = c.getString("Entry");
                String aka = c.getString("AKA");

                HashMap<String, String> contact = new HashMap<>();

                contact.put("title", title);
                contact.put("entry", entry);
                contact.put("aka", aka);

                disorderList.add(contact);

                adapter = new SimpleAdapter(
                        MainActivity.this, disorderList,
                        R.layout.model, new String[]{"title", "entry",
                        "aka"}, new int[]{R.id.title,
                        R.id.entry, R.id.aka});

                lv.setAdapter(adapter);

            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (MainActivity.this).adapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }
}