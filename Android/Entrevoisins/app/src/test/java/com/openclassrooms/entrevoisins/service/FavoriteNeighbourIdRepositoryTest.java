package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.repository.FavoriteNeighbourIdRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class FavoriteNeighbourIdRepositoryTest {
    private FavoriteNeighbourIdRepository favoriteNeighbourIdRepository;
    private NeighbourApiService neighbourApiService;
    private List<Neighbour> neighbourList;

    @Before
    public void setup() {
        neighbourApiService = DI.getNewInstanceApiService();
        neighbourList = neighbourApiService.getNeighbours();
        favoriteNeighbourIdRepository = FavoriteNeighbourIdRepository.getInstance();
        favoriteNeighbourIdRepository.setNeighbourApiService(neighbourApiService);

        if (favoriteNeighbourIdRepository.countNeighbour() == 0) {
            final int addedFavoriteNeighbours = 3;
            int neighbourListSize = neighbourList.size();
            for (int i = 0; i < addedFavoriteNeighbours & i < neighbourListSize; i++) {
                favoriteNeighbourIdRepository.addIdNeighbour(neighbourList.get(i).getId());
            }
        }
    }

    @Test
    public void addFavoriteNeighboursWithSuccess() {
        // Arrange
        Long expectedNeighbourId =  neighbourApiService.getNeighbours().get(10).getId();

        // Act
        favoriteNeighbourIdRepository.addIdNeighbour(expectedNeighbourId);

        // Assert
        assertTrue(favoriteNeighbourIdRepository.getIdList().contains(expectedNeighbourId));
    }

    @Test
    public void deleteFavoriteNeighboursWithSuccess() {
        // Arrange
        Long removedId = favoriteNeighbourIdRepository.getIdList().get(0);

        // Act
        favoriteNeighbourIdRepository.deleteIdNeighbour(removedId);

        // Assert
        assertFalse(favoriteNeighbourIdRepository.getIdList().contains(removedId));
    }


    @Test
    public void getFavoriteNeighboursWithSuccess() {
        // Arrange
        Long expectedFavoriteNeighbourId =  favoriteNeighbourIdRepository.getIdList().get(0);

        // Act
        Neighbour favoriteNeighbour = favoriteNeighbourIdRepository.getNeighboursFromList(expectedFavoriteNeighbourId, neighbourList);
        Long actual = favoriteNeighbour.getId();

        // Assert
        assertEquals(expectedFavoriteNeighbourId, actual);
    }

    @Test
    public void getFavoriteNeighboursRepositoryFromNeighboursApiWithSuccess() {
        // Arrange
        List<Long> expectedFavoriteNeighbourId = favoriteNeighbourIdRepository.getIdList();

        // Act
        List<Neighbour> actualFavoriteNeighbourList = favoriteNeighbourIdRepository.getNeighboursList();

        // Assert
        int actualFavoriteNeighbourListSize = actualFavoriteNeighbourList.size();
        for ( int i = 0; i < actualFavoriteNeighbourListSize; i++) {
            Long expectedId = expectedFavoriteNeighbourId.get(i);
            Long actualId = actualFavoriteNeighbourList.get(i).getId();
            assertEquals(expectedId, actualId);
        }
    }

    @Test
    public void addFavoriteNeighboursNotInApiServiceWithNoSuccess() {
        // Arrange
        Long noExpectedNeighbourId =  100000L;

        // Act
        favoriteNeighbourIdRepository.addIdNeighbour(noExpectedNeighbourId);

        // Assert
        assertFalse(favoriteNeighbourIdRepository.getIdList().contains(noExpectedNeighbourId));
    }

    @Test
    public void addFavoriteNeighboursAlreadyOnFavoriteNeighboursRepositoryWithNoSuccess() {
        // Arrange
        Long NeighbourId =  10L;
        favoriteNeighbourIdRepository.addIdNeighbour(NeighbourId);
        int noExpetedFavoriteNeighbourCount = favoriteNeighbourIdRepository.countNeighbour();

        // Act
        favoriteNeighbourIdRepository.addIdNeighbour(NeighbourId);

        // Assert
        int FavoriteNeighbourCount = favoriteNeighbourIdRepository.countNeighbour();
        assertEquals(FavoriteNeighbourCount, noExpetedFavoriteNeighbourCount);
    }

}
