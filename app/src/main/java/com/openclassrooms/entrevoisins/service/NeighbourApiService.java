package com.openclassrooms.entrevoisins.service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);


////////////////////////////////////////////////////


    /**
     * Deletes a favorite neighbour in favorite fragment
     * @param neighbour
     */
    void deleteFavorite(Neighbour neighbour);

    /**
     * get favorite neighbour list
     */
    List<Neighbour> getFavoritesNeighbours();

    /**
     * Switch add favorite / delete favorite in details activity, with FAB
     * @param neighbour
     */
    void toggleFavoriteNeighbour(@NonNull Neighbour neighbour);

}
