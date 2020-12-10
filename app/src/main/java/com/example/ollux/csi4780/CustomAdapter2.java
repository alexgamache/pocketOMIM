package com.example.ollux.csi4780;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomAdapter2 extends BaseAdapter implements Filterable {
    LayoutInflater inflater;
    Context context;
    Activity cntx;
    List<ItemsModel2> arrayList;
    List<ItemsModel2> arrayListFiltered;

    public CustomAdapter2(Context context, List<ItemsModel2> data) {
        this.context = context;
        this.arrayList = data;
        this.arrayListFiltered = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayListFiltered.size();
    }
    @Override
    public boolean isEnabled(int position) {
        return false;
    }
    @Override
    public Object getItem(int position) {
        return arrayListFiltered.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.contents,null);

        TextView section = view.findViewById(R.id.section);
        TextView omimtext = view.findViewById(R.id.OMIMtext);

        section.setText(arrayListFiltered.get(position).getSection());
        omimtext.setText(arrayListFiltered.get(position).getOmimtext());

        return view;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = arrayList.size();
                    filterResults.values = arrayList;
                }else{
                    List<ItemsModel2> resultsModel = new ArrayList<ItemsModel2>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(ItemsModel2 itemsModel:arrayList){
                        if(itemsModel.getSection().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }
                return filterResults;
            }
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrayListFiltered = (List<ItemsModel2>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}