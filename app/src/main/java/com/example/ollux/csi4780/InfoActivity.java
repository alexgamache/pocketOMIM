package com.example.ollux.csi4780;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InfoActivity extends AppCompatActivity {

    private TextView title;
    private TextView entry;
    private TextView aka;
    private ListView lv;
    private EditText et;
    public CustomAdapter2 adapter;
    public List<ItemsModel2> contentList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        contentList = new ArrayList<>();
        title = (TextView) findViewById(R.id.title);
        entry = (TextView) findViewById(R.id.entry);
        aka = (TextView) findViewById(R.id.aka);
        lv = (ListView) findViewById(R.id.contents);
        et = (EditText) findViewById(R.id.contentSearch);
        try {
            JSONObject jsonObj = new JSONObject(getIntent().getStringExtra("Details"));

            String titleString = jsonObj.getString("title");
            String entryString = jsonObj.getString("entry");
            String akaString = jsonObj.getString("aka");
            title.setText(titleString);
            entry.setText(entryString);
            aka.setText(akaString);
            String array = jsonObj.getString("contents");

            JSONObject jsonO = new JSONObject(array);

                String descriptionString = jsonO.getString("Description");
                String historyString = jsonO.getString("History");
                String clinicalString = jsonO.getString("Clinical Features");
                String diagnosisString = jsonO.getString("Diagnosis");
                String cytogeneticsString = jsonO.getString("Cytogenetics");
                String genophenoString = jsonO.getString("Genotype/Phenotype Correlations");

            adapter = new CustomAdapter2(this, contentList);

            lv.setAdapter(adapter);

                if(descriptionString == null || descriptionString.length() == 0) {

                }
                else {
                    ItemsModel2 description = new ItemsModel2("Description", descriptionString);
                    contentList.add(description);
                    adapter.notifyDataSetChanged();
                }

            if(historyString == null || historyString.length() == 0) {

            }
            else {
                ItemsModel2 history = new ItemsModel2("History", historyString);
                contentList.add(history);
                adapter.notifyDataSetChanged();
            }

            if(clinicalString == null || clinicalString.length() == 0) {

            }
            else {
                ItemsModel2 clinical = new ItemsModel2("Clinical Features", clinicalString);
                contentList.add(clinical);
                adapter.notifyDataSetChanged();
            }

            if(diagnosisString == null || diagnosisString.length() == 0) {

            }
            else {
                ItemsModel2 diagnosis = new ItemsModel2("Diagnosis", diagnosisString);
                contentList.add(diagnosis);
                adapter.notifyDataSetChanged();
            }

            if(cytogeneticsString == null || cytogeneticsString.length() == 0) {

            }
            else {
                ItemsModel2 cytogenetics = new ItemsModel2("Cytogenetics", cytogeneticsString);
                contentList.add(cytogenetics);
                adapter.notifyDataSetChanged();
            }

            if(genophenoString == null || genophenoString.length() == 0) {

            }
            else {
                ItemsModel2 genopheno = new ItemsModel2("Genotype/Phenotype Correlations", genophenoString);
                contentList.add(genopheno);
                adapter.notifyDataSetChanged();
            }

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

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}