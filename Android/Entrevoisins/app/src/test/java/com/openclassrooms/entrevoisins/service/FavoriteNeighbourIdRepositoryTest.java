package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.repository.FavoriteNeighbourIdRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FavoriteNeighbourIdRepositoryTest {
    private FavoriteNeighbourIdRepository favoriteNeighbourIdRepository;

    @Before
    public void setup() {
        favoriteNeighbourIdRepository = FavoriteNeighbourIdRepository.getInstance();
    }

    @Test
    public void addFavoriteNeighboursWithSuccess() {
        // Arrange
        Long expectedNeighbourId =  10L;

        // Act
        favoriteNeighbourIdRepository.addIdNeighbour(expectedNeighbourId);

        // Assert
        assertTrue(favoriteNeighbourIdRepository.getIdList().contains(expectedNeighbourId));
    }

    @Test
    public void deleteFavoriteNeighboursWithSuccess() {
        // Arrange
        Long removedId = 10L;
        favoriteNeighbourIdRepository.addIdNeighbour(removedId);

        // Act
        favoriteNeighbourIdRepository.deleteIdNeighbour(removedId);

        // Assert
        assertFalse(favoriteNeighbourIdRepository.getIdList().contains(removedId));
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

    @Test
    public void deleteFavoriteNeighboursNotOnFavoriteNeighboursRepositoryWithNoSuccess() {
        // Arrange
        Long NeighbourId =  20L;
        favoriteNeighbourIdRepository.deleteIdNeighbour(NeighbourId);
        int ExpetedFavoriteNeighbourCount = favoriteNeighbourIdRepository.countNeighbour();

        // Act
        favoriteNeighbourIdRepository.deleteIdNeighbour(NeighbourId);

        // Assert
        int FavoriteNeighbourCount = favoriteNeighbourIdRepository.countNeighbour();
        assertEquals(FavoriteNeighbourCount, ExpetedFavoriteNeighbourCount);
    }

}
