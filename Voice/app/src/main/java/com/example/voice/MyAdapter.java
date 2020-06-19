package com.example.voice;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<ListItems> listItems = new ArrayList<ListItems>();
    public MyAdapter(){}

    public void addvalue(String title, String link, String description, String postdate){
        ListItems item = new ListItems();
        item.setTitle(title);
        item.setLink(link);
        item.setDescription(description);
        item.setPostdate(postdate);
        listItems.add(item);
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_listview,parent,false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView link = (TextView) convertView.findViewById(R.id.link);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView postdate = (TextView) convertView.findViewById(R.id.postdate);

        final ListItems listViewItem = listItems.get(position);

        title.setText(listViewItem.getTitle());
        link.setText(listViewItem.getLink());
        description.setText(listViewItem.getDescription());
        postdate.setText("작성날짜: "+listViewItem.getPostdate());

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent new_site = new Intent(Intent.ACTION_VIEW, Uri.parse(listViewItem.getLink()));
                context.startActivity(new_site);
            }
        });
        return convertView;
    }
}

