package com.openclassrooms.entrevoisins.service;

import android.support.annotation.NonNull;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;


/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoritesNeighbours = new ArrayList<>();

    /**
     * get Neighbour list
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * get favorites list
     * @return
     */
    @Override
    public List<Neighbour> getFavoritesNeighbours() {
        return favoritesNeighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        favoritesNeighbours.remove(neighbour);
    }

    /**
     *delete favorite neighbour
     */
    @Override
    public void deleteFavorite(Neighbour neighbour) {
        favoritesNeighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
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
        if(favoritesNeighbours.contains(neighbour)){
            favoritesNeighbours.remove(neighbour);
        } else{
            favoritesNeighbours.add(neighbour);
        }
    }
}







