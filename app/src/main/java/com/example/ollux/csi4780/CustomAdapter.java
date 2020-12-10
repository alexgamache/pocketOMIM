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

public class CustomAdapter extends BaseAdapter implements Filterable {
    LayoutInflater inflater;
    Context context;
    Activity cntx;
    List<ItemsModel> arrayList;
    List<ItemsModel> arrayListFiltered;

    public CustomAdapter(Context context, List<ItemsModel> data) {
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
    public Object getItem(int position) {
        return arrayListFiltered.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.model,null);

        TextView title = view.findViewById(R.id.title);
        TextView entry = view.findViewById(R.id.entry);
        TextView aka = view.findViewById(R.id.aka);

        title.setText(arrayListFiltered.get(position).getTitle());
        entry.setText(arrayListFiltered.get(position).getEntry());
        aka.setText(arrayListFiltered.get(position).getAka());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoActivity.class);
                Gson gson = new Gson();
                String jsonG = gson.toJson(arrayListFiltered.get(position));
                String jsonG2 = jsonG.replace("\\", "");
                intent.putExtra("Details", jsonG);
                context.startActivity(intent);
            }
        });

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
                    List<ItemsModel> resultsModel = new ArrayList<ItemsModel>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(ItemsModel itemsModel:arrayList){
                        if(itemsModel.getTitle().toLowerCase().contains(searchStr)){
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
                    arrayListFiltered = (List<ItemsModel>) results.values;
                    notifyDataSetChanged();
            }
        };
        return filter;
    }
}