package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteFavoriteEvent {

    /**
     * Neighbour to delete
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
