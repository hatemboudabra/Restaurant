package com.livrini.restaurant.controller;

import com.livrini.restaurant.dto.AvisDto;
import com.livrini.restaurant.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
public class AvisController {
    private final AvisService avisService;

    @Autowired
    public AvisController(AvisService avisService) {
        this.avisService = avisService;
    }

    @PostMapping
    public AvisDto createAvis(@RequestBody AvisDto avisDto) {
        return avisService.saveAvis(avisDto);
    }

    @PutMapping("/{id}")
    public AvisDto updateAvis(@PathVariable Long id, @RequestBody AvisDto avisDto) {
        return avisService.updateAvis(id, avisDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAvis(@PathVariable Long id) {
        avisService.deleteAvis(id);
    }

    @GetMapping("/{id}")
    public AvisDto getAvisById(@PathVariable Long id) {
        return avisService.getAvisById(id);
    }

    @GetMapping
    public List<AvisDto> getAllAvis() {
        return avisService.getAllAvis();
    }

}
