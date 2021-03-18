package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavoritesWithSuccess(){
        //given
        Neighbour neighbour = service.getNeighbours().get(0);
        //when
        service.toggleFavoriteNeighbour(neighbour);
        //then
        List<Neighbour> favoriteNeighbour = service.getFavoritesNeighbours();
        assertTrue(favoriteNeighbour.contains(neighbour));
    }

    @Test
    public void addFavoriteWithSuccess(){
        //given
        Neighbour neighbour = service.getNeighbours().get(0);
        //when
        service.toggleFavoriteNeighbour(neighbour);
        //then
        assertTrue(service.getFavoritesNeighbours().contains(neighbour));
    }

    @Test
    public void deleteFavoriteFromFabWithSuccess(){
        Neighbour neighbour = service.getNeighbours().get(0);
        service.toggleFavoriteNeighbour(neighbour);
        service.toggleFavoriteNeighbour(neighbour);
        assertFalse(service.getFavoritesNeighbours().contains(neighbour));
    }

    @Test
    public void deleteFavoriteFromFavoriteFragmentWithSuccess(){
        Neighbour expectedDeletedNeighbour = service.getNeighbours().get(0);
        service.toggleFavoriteNeighbour(expectedDeletedNeighbour);
        service.deleteFavorite(expectedDeletedNeighbour);
        assertFalse(service.getFavoritesNeighbours().contains(expectedDeletedNeighbour));
        assertTrue(service.getNeighbours().contains((expectedDeletedNeighbour)));
    }
}
