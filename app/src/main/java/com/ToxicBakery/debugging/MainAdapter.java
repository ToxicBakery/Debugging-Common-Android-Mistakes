package com.ToxicBakery.debugging;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class MainAdapter extends RecyclerView.Adapter<ActivityViewHolder> {

    private final List<Pair<Integer, Class<? extends Activity>>> activityClasses;

    MainAdapter(List<Pair<Integer, Class<? extends Activity>>> activityClasses) {
        this.activityClasses = activityClasses;
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder holder, int position) {
        Pair<Integer, Class<? extends Activity>> pair = activityClasses.get(position);
        holder.bind(pair);
    }

    @Override
    public int getItemCount() {
        return activityClasses.size();
    }

}
