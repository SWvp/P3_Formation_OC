package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment  {
    @NonNull
    private NeighbourApiService mNeighbourApiService;
    private RecyclerView mRecyclerView;
    private MyNeighbourRecyclerViewAdapter mAdapter;
    List<Neighbour> newFavoriteList = new ArrayList<>();




    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNeighbourApiService = DI.getNeighbourApiService();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        initList();

        return view;
    }
    /**
     * start list
     */
    private void initList() {
        mAdapter = new MyNeighbourRecyclerViewAdapter(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * return list from service, and send to adapter
     */
    public void onNewFavoriteNeighbour() {
        newFavoriteList = mNeighbourApiService.getFavoriteNeighbours();
        mAdapter.submitList(newFavoriteList);
    }

    /**
     * refresh on eventBus event
     */
    private void refreshList() {
        List<Neighbour> newFavoriteList = mNeighbourApiService.getFavoriteNeighbours();
        mAdapter.submitList(newFavoriteList);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    /**
     * eventBus registered
     */
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /**
     * eventBus unregistered
     */
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Subscribe to delete neighbour event to refresh the list
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {

        refreshList();
    }

    /**
     * Subscribe to delete favorite event
     * @param event
     */
    @Subscribe
    public void onDeleteFavorite(DeleteFavoriteEvent event) {
        mNeighbourApiService.deleteFavorite(event.favoriteNeighbour);

        refreshList();
    }

}
