package com.linkstreet.vinoth.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linkstreet.vinoth.myapplication.db.User;

import java.util.List;

/**
 * Created by vinoth on 20/4/17.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<User> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }

    public MyAdapter(List<User> userList) {
        mDataset = userList;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setUserList(holder.mView, position);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    private void setUserList(View view, int position){
        User topic = mDataset.get(position);
        TextView name = (TextView) view.findViewById(R.id.firstname);
        name.setText("FIRST NAME:" + " " +topic.getFirst_name());
    }
}
