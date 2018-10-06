package com.example.shann.proj2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class RepAdapter extends RecyclerView.Adapter<RepAdapter.RepresentativeViewHolder> {
    private List<Representative> reps;
    private final ArrayList<Representative> house;
    private final ArrayList<Representative> senate;
    private String msg;
    private Context mContext;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RepAdapter(ArrayList<Representative> house, ArrayList<Representative> senate, String msg, Context context) {
        this.reps = new ArrayList<>(house);
        this.reps.addAll(senate);
        this.house = house;
        this.senate = senate;
        this.msg = msg;
        mContext = context;
    }

    public static class RepresentativeViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        Button email;
        Button website;
        TextView party;

        ImageView picture;
        RepresentativeViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (Button) itemView.findViewById(R.id.email);
            website = (Button) itemView.findViewById(R.id.website);
            picture = (ImageView) itemView.findViewById(R.id.picture);
            party = (TextView) itemView.findViewById(R.id.party);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RepAdapter.RepresentativeViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_custom, viewGroup, false);
        RepresentativeViewHolder rvh = new RepresentativeViewHolder(v);
        return rvh;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return reps.size();
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RepresentativeViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Representative rep = reps.get(position);
        final ImageView pt = (ImageView) holder.picture;
        holder.name.setText(rep.getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailedView.class);
                i.putExtra(MainActivity.MESSAGE, msg);
                i.putExtra(MainActivity.REPRESENTATIVES, house);
                i.putExtra(MainActivity.SENATORS, senate);
                i.putExtra(MainActivity.REPRESENTATIVE, rep);
                pt.buildDrawingCache();
                Bitmap bmp = pt.getDrawingCache();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                i.putExtra(MainActivity.PICTURE, byteArray);
                mContext.startActivity(i);
            }
        });
        // download the picture to the ImageView
        try {
            new DownloadImageTask(holder.picture)
                    .execute(rep.getPicture());
        } catch (Exception e) {
            new DownloadImageTask(holder.picture).execute(("https://wingslax.com/wp-content/uploads/2017/12/no-image-available.png"));
        }
        holder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailedView.class);
                i.putExtra(MainActivity.MESSAGE, msg);
                i.putExtra(MainActivity.REPRESENTATIVES, house);
                i.putExtra(MainActivity.SENATORS, senate);
                i.putExtra(MainActivity.REPRESENTATIVE, rep);
                pt.buildDrawingCache();
                Bitmap bmp = pt.getDrawingCache();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                i.putExtra(MainActivity.PICTURE, byteArray);
                mContext.startActivity(i);
            }
        });
        if (!rep.getEmail().equals("")) {
            holder.email.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(rep.getEmail()));
                    mContext.startActivity(i);
                }
            });
        } else {
            holder.email.setCompoundDrawablesWithIntrinsicBounds(R.drawable.noemail, 0, 0, 0);
            holder.email.setBackgroundResource(0);
            holder.email.setText("None");
        }
        if (!rep.getWebsite().equals("")) {
            holder.website.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(rep.getWebsite()));
                    mContext.startActivity(i);
                }
            });
        } else {
            holder.website.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fourohfour, 0, 0, 0);
            holder.website.setBackgroundResource(0);
            holder.website.setText("None");
        }
        holder.party.setText(rep.getParty());
        holder.party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailedView.class);
                i.putExtra(MainActivity.MESSAGE, msg);
                i.putExtra(MainActivity.REPRESENTATIVES, house);
                i.putExtra(MainActivity.SENATORS, senate);
                i.putExtra(MainActivity.REPRESENTATIVE, rep);
                pt.buildDrawingCache();
                Bitmap bmp = pt.getDrawingCache();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                i.putExtra(MainActivity.PICTURE, byteArray);
                mContext.startActivity(i);
            }
        });
    }


}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            try {
                InputStream in = new java.net.URL("https://wingslax.com/wp-content/uploads/2017/12/no-image-available.png").openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception m) {

            }

        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}