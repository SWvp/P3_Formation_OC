package com.openclassrooms.entrevoisins.service;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.openclassrooms.entrevoisins.model.Neighbour;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoriteNeighbour = new ArrayList<>();





    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {

        return neighbours;
    }

    /**
     * get favorite list
     * @return
     */
    @Override
    public List<Neighbour> getFavoriteNeighbours() {

        return favoriteNeighbour;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {

        neighbours.remove(neighbour);
        favoriteNeighbour.remove(neighbour);
    }


    /**
     *delete favorite neighbour
     */
    @Override
    public void deleteFavorite(Neighbour neighbour) {

        favoriteNeighbour.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {

        neighbours.add(neighbour);
    }


    /**
     * favorite will be added or deleted from favorite List
     * */
    @Override
    public void toggleFavoriteNeighbour(@NonNull Neighbour neighbour) {
        if(favoriteNeighbour.contains(neighbour)){
            favoriteNeighbour.remove(neighbour);
        } else{
            favoriteNeighbour.add(neighbour);
        }
    }
}







