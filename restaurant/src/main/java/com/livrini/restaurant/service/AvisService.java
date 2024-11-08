package com.livrini.restaurant.service;

import com.livrini.restaurant.dto.AvisDto;

import java.util.List;

public interface AvisService {
    AvisDto saveAvis(AvisDto avisDto);
    AvisDto updateAvis(Long id, AvisDto avisDto);
    void deleteAvis(Long id);
    AvisDto getAvisById(Long id);
    List<AvisDto> getAllAvis();
}
