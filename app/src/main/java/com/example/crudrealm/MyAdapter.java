package com.example.crudrealm;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Frog> {
    ArrayList<Frog> data;
    Context context;

    public MyAdapter(@NonNull Context context,ArrayList<Frog> data) {
        super(context,R.layout.component,data);
        this.data=data;
        this.context = context;
    }

    private class viewHolder {
        TextView txtFrog;
        ImageView img;
    }

}
