package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.CreatePantingDto;
import com.paintingscollectors.model.dto.PaintingDto;
import com.paintingscollectors.model.entity.Painting;

import java.util.List;
import java.util.Set;

public interface PaintingService {
    void paintingCreate(CreatePantingDto createPanting);

    Painting getPaintingById(long paintingId);
    Set<PaintingDto> addToMyFavoritesList(long paintingId);

    void removePaintingFromFavorites(long paintingId);

    void vote(long paintingId);
    List<PaintingDto> getAllPaintingsByRate();

    void remove(long id);


}
