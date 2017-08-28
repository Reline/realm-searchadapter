package io.github.reline.realmsearchadaptersample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class CatViewHolder extends RecyclerView.ViewHolder {
    public CatViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(Cat cat) {
        TextView nameTextView = itemView.findViewById(R.id.name);
        nameTextView.setText(cat.name);
    }
}
