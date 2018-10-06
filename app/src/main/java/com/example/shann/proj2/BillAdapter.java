package com.example.shann.proj2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.view.View.GONE;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {
    private List<Bill> bills;
    private Context mContext;

    // Provide a suitable constructor (depends on the kind of dataset)
    public BillAdapter(List<Bill> bills, Context context) {
        this.bills = bills;
        mContext = context;

    }

    public static class BillViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageButton bill;
        TextView billdate;
        TextView billnumber;
        TextView billtitle;
        BillViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            billdate = (TextView) itemView.findViewById(R.id.billdate);
            bill = (ImageButton) itemView.findViewById(R.id.bill);
            billnumber = (TextView) itemView.findViewById(R.id.billnumber);
            billtitle = (TextView) itemView.findViewById(R.id.billtitle);
            billtitle.setVisibility(View.GONE);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BillAdapter.BillViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                                  int viewType) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_bill, viewGroup, false);
        BillViewHolder bvh = new BillViewHolder(v);
        return bvh;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return bills.size();
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final BillViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Bill bill = bills.get(position);
        holder.billdate.setText("Date introduced: " + bill.getDate());
        holder.billnumber.setText("Bill num " + bill.getNumber());
        holder.billtitle.setText("Bill name: " + bill.getName());
        holder.bill.setImageResource(bill.getDrawableId());
        if (bill.getDrawableId() == R.drawable.expand) {
            holder.billtitle.setVisibility(View.GONE);
        } else {
            holder.billtitle.setVisibility(View.VISIBLE);
        }
        if (!bill.getUrl().equals("")) {
            holder.billtitle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(bill.getUrl()));
                    mContext.startActivity(i);
                }
            });
            holder.billnumber.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(bill.getUrl()));
                    mContext.startActivity(i);
                }
            });
            holder.billdate.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(bill.getUrl()));
                    mContext.startActivity(i);
                }
            });
        }
        final TextView billtitle = holder.billtitle;
        holder.bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (billtitle.getVisibility() == View.GONE) {
                    billtitle.setVisibility(View.VISIBLE);
                    holder.bill.setImageResource(R.drawable.collapse);
                    bill.setDrawableId(R.drawable.collapse);
                } else {
                    billtitle.setVisibility(View.GONE);
                    holder.bill.setImageResource(R.drawable.expand);
                    bill.setDrawableId(R.drawable.expand);
                }
            }
        });
    }

}
