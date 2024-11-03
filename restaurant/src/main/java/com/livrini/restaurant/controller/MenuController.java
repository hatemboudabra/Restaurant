package com.livrini.restaurant.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livrini.restaurant.dto.MenuDto;
import com.livrini.restaurant.entity.Menu;
import com.livrini.restaurant.fileManager.FileFilter;
import com.livrini.restaurant.service.MenuServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MenuController {

    private final MenuServiceImpl menuService;
    @Autowired
    private FileFilter fileFilter;
    public MenuController(MenuServiceImpl menuService) {
        this.menuService = menuService;
    }
    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public List<Menu> menus() {
        List<Menu> menus = new ArrayList<>();
        try {
            menus = menuService.findallMenu();
        } catch (Exception e) {
            System.out.println("Erreur " + e.getMessage());
        }
        return menus;
    }
    @GetMapping("/images/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) {
        try {
            String repertoireImage = "restaurant/src/main/resources/images"; // Chemin vers le répertoire des images
            File imageFile = new File(repertoireImage, filename);

            if (imageFile.exists()) {
                byte[] imageBytes = FileUtils.readFileToByteArray(imageFile);
                return ResponseEntity.ok()
                        .header("Content-Type", "image/jpeg") // Assurez-vous que le type de contenu correspond au type de votre image
                        .body(imageBytes);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/ajout/menu", method = RequestMethod.POST, headers = "accept=Application/json")
    public MenuDto ajouter(@RequestParam("image") MultipartFile file,
                           String menu) throws JsonProcessingException {

        MenuDto menuDto = new ObjectMapper().readValue(menu, MenuDto.class);
        String repertoireImage = "restaurant/src/main/resources/images"; // Path to the images directory
        File repertoire = new File(repertoireImage);

        if (!repertoire.exists()) {
            boolean repertoireCree = repertoire.mkdirs();
            if (!repertoireCree) {
                throw new RuntimeException("Impossible de créer le répertoire 'images'");
            }
        }

        String nomFichier = file.getOriginalFilename();
        String nouveauNom = FilenameUtils.getBaseName(nomFichier) + "." + FilenameUtils.getExtension(nomFichier);
        File fichierDuServeur = new File(repertoire, nouveauNom);

        try {
            FileUtils.writeByteArrayToFile(fichierDuServeur, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        menuDto.setImage(nouveauNom);
        return menuService.addMenu(menuDto);
    }
    @GetMapping("/menu/{id}")
    public Optional<Menu> findMenuById(@PathVariable Long id) {
        return menuService.findById(id);
    }
    @PutMapping(value = "/update/menu/{id}", headers = "accept=application/json")
    public Menu updateMenu(@PathVariable Long id,
                           @RequestParam("image") MultipartFile file,
                           @RequestParam("menu") String menu) throws JsonProcessingException {

        MenuDto menuDto = new ObjectMapper().readValue(menu, MenuDto.class);
        String repertoireImage = "restaurant/src/main/resources/images";
        File repertoire = new File(repertoireImage);

        if (!repertoire.exists()) {
            boolean repertoireCree = repertoire.mkdirs();
            if (!repertoireCree) {
                throw new RuntimeException("Unable to create the 'images' directory");
            }
        }

        String nomFichier = file.getOriginalFilename();
        String nouveauNom = FilenameUtils.getBaseName(nomFichier) + "." + FilenameUtils.getExtension(nomFichier);
        File fichierDuServeur = new File(repertoire, nouveauNom);

        try {
            FileUtils.writeByteArrayToFile(fichierDuServeur, file.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to save the file: " + e.getMessage());
        }

        menuDto.setImage(nouveauNom);
        return menuService.updateMenu(id,menuDto);
    }
    @DeleteMapping("/delete/menu/{id}")
    public String deleteMenu(@PathVariable Long id) {
        menuService.DeleteMenu(id);
        return "Menu with ID " + id + " deleted successfully.";
    }
}

