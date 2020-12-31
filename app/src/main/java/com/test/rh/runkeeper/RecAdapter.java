package com.test.rh.runkeeper;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> {
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;

    private List<RaceModel> list;
    private String packageName;

    public RecAdapter(List<RaceModel> list, String packageName) {
        this.list = list;
        this.packageName = packageName;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == ITEM_VIEW_TYPE_HEADER)
            view = inflater.inflate(R.layout.header_item, parent, false);
        else
            view = inflater.inflate(R.layout.item_race, parent, false);

        return new RecViewHolder(view, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getViewType() == 0)
            return ITEM_VIEW_TYPE_HEADER;
        return ITEM_VIEW_TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {
        RaceModel race = list.get(position);

        holder.bind(race);

        holder.itemView.setOnClickListener(v -> {
            boolean expanded = race.isExpanded();
            race.setExpanded(!expanded);
            notifyItemChanged(position);
            try {
                notifyItemChanged(position - 1);
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                notifyItemChanged(position + 1);
            }catch (Exception e){
                e.printStackTrace();
            }
           // notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView time;
        private TextView elevation;
        private TextView year;
        private ImageView logo;
        private View subItem;

        public RecViewHolder(View itemView, int viewType) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            time = itemView.findViewById(R.id.sub_item_time);

            if (viewType == ITEM_VIEW_TYPE_ITEM) {
                logo = itemView.findViewById(R.id.imageView);
                elevation = itemView.findViewById(R.id.sub_item_elevation);
                year = itemView.findViewById(R.id.sub_item_year);
                subItem = itemView.findViewById(R.id.sub_item);
            }
        }

        private void bind(RaceModel movie) {
            if (movie.getViewType() == ITEM_VIEW_TYPE_HEADER) {
                title.setText(movie.getTitle());
                time.setText(movie.getTime());
            } else {
                boolean expanded = movie.isExpanded();

                subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

                Resources res = itemView.getResources();
                String mDrawableName = movie.getFileName();
                int resID = res.getIdentifier(mDrawableName, "drawable", packageName);
                Drawable drawable = res.getDrawable(resID);

                Glide.with(itemView)
                        .load(drawable)
                        .override(200, 200)
                        .into(logo);
                title.setText(movie.getTitle());
                elevation.setText(movie.getMaxElevation() + " ft");
                time.setText("Time: " + movie.getTime());
                year.setText("Year: " + movie.getYear());
            }
        }
    }
}
