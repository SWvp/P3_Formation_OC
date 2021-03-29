package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours = new ArrayList<>();
    private final boolean isFavorite;

    public MyNeighbourRecyclerViewAdapter(boolean isFavorite){
        this.isFavorite = isFavorite;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                     EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));

            }
        });

        /**
         * Start new activity from item_list_name
         */
        holder.itemFragment.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Context mContext = v.getContext();
              Intent detailActivity = new Intent(mContext, DetailsActivity.class);
              detailActivity.putExtra("full id" , neighbour);
              ((Activity) mContext).startActivityForResult(detailActivity , 1);
          }
      });
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    /**
     * get list of neighbour or favorites neighbour and set adapter
     * @param newNeighbourList
     */
    public void submitList(List<Neighbour> newNeighbourList) {
        mNeighbours.clear();
        mNeighbours.addAll(newNeighbourList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;
        View itemFragment = itemView.findViewById(R.id.item_fragment);

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
