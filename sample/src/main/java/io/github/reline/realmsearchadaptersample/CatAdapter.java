package io.github.reline.realmsearchadaptersample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.realm.OrderedRealmCollection;
import xyz.projectplay.realmsearchadapter.RealmSearchAdapter;

public class CatAdapter extends RealmSearchAdapter<Cat, CatViewHolder> {

    public CatAdapter(@Nullable OrderedRealmCollection<Cat> data, @NonNull String filterKey) {
        super(data, filterKey);
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CatViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cat_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
