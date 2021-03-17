package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a favorite neighbour
 */
public class DeleteFavoriteEvent {

    /**
     * Favorite neighbour to delete
     */
    public Neighbour favoriteNeighbour;

    /**
     * Constructor.
     * @param favoriteNeighbour
     */
    public DeleteFavoriteEvent(Neighbour favoriteNeighbour) {
        this.favoriteNeighbour = favoriteNeighbour;
    }
}
