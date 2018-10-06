package com.example.shann.proj2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    private RecyclerView mRecyclerView1;
    private RecyclerView.Adapter mAdapter1;
    private RecyclerView.LayoutManager mLayoutManager1;
    private ArrayList<Representative> reps;
    private ArrayList<Representative> sens;
    private String msg;
    private Representative rep;
    private byte[] byteArray;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle argument = getArguments();
            ArrayList<Representative> reps = (ArrayList<Representative>) argument.getSerializable(MainActivity.REPRESENTATIVES);
            ArrayList<Representative> sens = (ArrayList<Representative>) argument.getSerializable(MainActivity.SENATORS);
            String msg = argument.getString(MainActivity.MESSAGE);
            final Representative rep = (Representative) argument.getSerializable(MainActivity.REPRESENTATIVE);
            byte[] byteArray = argument.getByteArray(MainActivity.PICTURE);
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            this.reps = reps;
            this.sens = sens;
            this.msg = msg;
            this.rep = rep;
            this.byteArray = byteArray;
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        setRetainInstance(true);
        mRecyclerView1 = (RecyclerView) view.findViewById(R.id.my_recycler_view1);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView1.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager1 = new LinearLayoutManager(getActivity());
        mRecyclerView1.setLayoutManager(mLayoutManager1);
        mAdapter1 = new BillAdapter(rep.getBills(), getActivity());
        mRecyclerView1.setAdapter(mAdapter1);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.appbar1);
        ImageView image = (ImageView) toolbar.findViewById(R.id.picture1);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        image.setImageBitmap(bmp);
        TextView name = (TextView) toolbar.findViewById(R.id.name1);
        name.setText(rep.getName());
        TextView party = (TextView) toolbar.findViewById(R.id.party1);
        party.setText(rep.getParty());
        if (!rep.getWebsite().equals("")) {
            toolbar.findViewById(R.id.website1).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(rep.getWebsite()));
                    startActivity(i);
                }
            });
        }
        if (!rep.getEmail().equals("")) {
            toolbar.findViewById(R.id.email1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(rep.getEmail()));
                    startActivity(i);
                }
            });
        }
        return view;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Save the values you need into "outState"
        outState.putSerializable(MainActivity.REPRESENTATIVES, reps);
        outState.putSerializable(MainActivity.SENATORS, sens);
        outState.putSerializable(MainActivity.REPRESENTATIVE, rep);
        outState.putString(MainActivity.MESSAGE, msg);
        outState.putByteArray(MainActivity.PICTURE, byteArray);
        super.onSaveInstanceState(outState);
    }
}

