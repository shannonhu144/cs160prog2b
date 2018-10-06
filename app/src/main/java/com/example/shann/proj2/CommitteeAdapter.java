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

public class CommitteeAdapter extends RecyclerView.Adapter<CommitteeAdapter.CommitteeViewHolder> {
    private List<String> coms;
    private Context mContext;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CommitteeAdapter(List<String> coms, Context context) {
        this.coms = coms;
        mContext = context;
    }

    public static class CommitteeViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageButton committee;
        TextView committeename;
        TextView subCommitteeName;
        CommitteeViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            committeename = (TextView) itemView.findViewById(R.id.committeename);
            committee = (ImageButton) itemView.findViewById(R.id.committee);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CommitteeAdapter.CommitteeViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_committee, viewGroup, false);
        CommitteeViewHolder cvh = new CommitteeViewHolder(v);
        return cvh;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return coms.size();
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CommitteeViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String com = coms.get(position);

        holder.committeename.setText(com);
    }


}
