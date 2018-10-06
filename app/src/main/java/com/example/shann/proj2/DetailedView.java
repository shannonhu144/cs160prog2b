package com.example.shann.proj2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailedView extends AppCompatActivity {
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private ArrayList<Representative> reps;
    private ArrayList<Representative> sens;
    private String msg;
    private byte[] byteArray;
    private Representative rep;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_detailed);
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // specify an adapter (see also next example)
        ArrayList<Representative> reps = (ArrayList<Representative>) getIntent().getSerializableExtra(MainActivity.REPRESENTATIVES);
        ArrayList<Representative> sens = (ArrayList<Representative>) getIntent().getSerializableExtra(MainActivity.SENATORS);
        String msg = getIntent().getStringExtra(MainActivity.MESSAGE);
        final Representative rep = (Representative) getIntent().getSerializableExtra(MainActivity.REPRESENTATIVE);
        byte[] byteArray = getIntent().getExtras().getByteArray(MainActivity.PICTURE);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        this.reps = reps;
        this.sens = sens;
        this.msg = msg;
        this.rep = rep;
        this.byteArray = byteArray;
        setupViewPager(mViewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar1);
        ImageView image = (ImageView) toolbar.findViewById(R.id.picture1);
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
        } else {
            Button website = toolbar.findViewById(R.id.website1);
            website.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fourohfour, 0, 0, 0);
            website.setBackgroundResource(0);
            website.setText("None");
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
        } else {
            Button email = toolbar.findViewById(R.id.email1);
            email.setCompoundDrawablesWithIntrinsicBounds(R.drawable.noemail, 0, 0, 0);
            email.setBackgroundResource(0);
            email.setText("None");
        }
        ImageButton bkbutton = toolbar.findViewById(R.id.back1);
        bkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack1();
            }
        });
        setSupportActionBar(toolbar);

    }
    public void toggle_contents(View v) {

    }
    public void goBack1() {
        Intent intent = new Intent(this, CongressionalView.class);
        intent.putExtra(MainActivity.REPRESENTATIVES, reps);
        intent.putExtra(MainActivity.SENATORS, sens);
        intent.putExtra(MainActivity.MESSAGE, msg);
        startActivity(intent);
    }
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        Tab1Fragment tab1 = new Tab1Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.REPRESENTATIVES, reps);
        bundle.putSerializable(MainActivity.SENATORS, sens);
        bundle.putSerializable(MainActivity.REPRESENTATIVE, rep);
        bundle.putString(MainActivity.MESSAGE, msg);
        bundle.putByteArray(MainActivity.PICTURE, byteArray);
        tab1.setArguments(bundle);
        Tab2Fragment tab2 = new Tab2Fragment();
        tab2.setArguments(bundle);
        adapter.addFragment(tab1, "Sponsored Bills");
        adapter.addFragment(tab2, "Committees");
        viewPager.setAdapter(adapter);
    }
}
