package com.example.medicare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReportViewAdapter extends RecyclerView.Adapter<ReportViewAdapter.ViewHolder> {
    private static final String TAG = "ReportViewAdapter";

    private ArrayList<String> mmDate = new ArrayList<String>();
    private ArrayList<String> mmTest = new ArrayList<String>();
    private ArrayList<String> mmsummary = new ArrayList<String>();
    private ArrayList<String> mmimage = new ArrayList<String>();
    private Context mContext;

    public ReportViewAdapter(ArrayList<String> mmDate, ArrayList<String> mmTest, ArrayList<String> mmsummary, ArrayList<String> mmimage, Context mContext) {
        this.mmDate = mmDate;
        this.mmTest = mmTest;
        this.mmsummary = mmsummary;
        this.mmimage = mmimage;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mdate.setText(mmDate.get(position));
        holder.mtest.setText(mmTest.get(position));
        holder.msummary.setText(mmsummary.get(position));
        holder.mimages.setText(mmimage.get(position));

    }

    @Override
    public int getItemCount() {
        return mmTest.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mdate,mtest,msummary,mimages;
        LinearLayout mparentlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mdate = itemView.findViewById(R.id.date);
            mtest = itemView.findViewById(R.id.test);
            msummary = itemView.findViewById(R.id.summary);
            mimages = itemView.findViewById(R.id.images);
            mparentlayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
