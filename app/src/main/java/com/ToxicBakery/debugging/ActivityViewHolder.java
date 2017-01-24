package com.ToxicBakery.debugging;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

class ActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView textView;
    private Class<? extends Activity> activityClass;

    ActivityViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(android.R.id.text1);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), activityClass);
        view.getContext().startActivity(intent);
    }

    void bind(Pair<Integer, Class<? extends Activity>> pair) {
        textView.setText(pair.first);
        activityClass = pair.second;
    }

}
