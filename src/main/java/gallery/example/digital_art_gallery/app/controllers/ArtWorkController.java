package gallery.example.digital_art_gallery.app.controllers;

import gallery.example.digital_art_gallery.app.dtos.artwork.ArtWorkCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artwork.ArtWorkResponseDTO;
import gallery.example.digital_art_gallery.services.ArtWorkService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artworks")
public class ArtWorkController {

    private final ArtWorkService artWorkService;

    public ArtWorkController(ArtWorkService artWorkService) {
        this.artWorkService = artWorkService;
    }

    @PostMapping
    public ResponseEntity<ArtWorkResponseDTO> generateArtWork(@Valid @RequestBody ArtWorkCreateDTO newArtWork) {
        ArtWorkResponseDTO createdArtWork = artWorkService.createArtWork(newArtWork);
        return ResponseEntity.status(201).body(createdArtWork);
    }
}
