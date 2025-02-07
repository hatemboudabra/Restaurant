package com.livrini.restaurant.controller;

import com.livrini.restaurant.dto.AvisDto;
import com.livrini.restaurant.entity.Avis;
import com.livrini.restaurant.service.AvisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/avis")
@CrossOrigin(origins = "*")

public class AvisController {
    private final AvisService avisService;

    @Autowired
    public AvisController(AvisService avisService) {
        this.avisService = avisService;
    }

    @PostMapping
    public ResponseEntity<Avis> addAvis( @RequestBody AvisDto avisDto) {
        try {
            Avis avis = avisService.addAvis(avisDto);
            return ResponseEntity.ok(avis);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Avis>> getAllAvis() {
        List<Avis> avisList = avisService.getallAvis();
        return ResponseEntity.ok(avisList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avis> getAvis(@PathVariable Long id) {
        Optional<Avis> avisOptional = avisService.getAvis(id);
        return avisOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avis> updateAvis(@PathVariable Long id, @RequestBody AvisDto avisDto) {
        Avis updatedAvis = avisService.updateAvis(id, avisDto);
        return ResponseEntity.ok(updatedAvis);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvis(@PathVariable Long id) {
        avisService.deleteAvis(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/average-rating/{menuId}")
    public Double getAverageRatingByMenu(@PathVariable Long menuId) {
        return avisService.calculateAverageRatingByMenu(menuId);
    }

}
